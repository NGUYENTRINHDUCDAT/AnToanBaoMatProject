package controller;

import vnpay.Config;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet("/vnpay")
public class VnPayServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        String vnp_Version = "2.1.0";
        String vnp_Command = "pay";
        String orderType   = "other";

        // parseLong thay vì Long.valueOf
        long   amount    = Long.parseLong(req.getParameter("amount")) * 100;
        String bankCode  = req.getParameter("bankCode");

        String vnp_TxnRef  = Config.getRandomNumber(8);
        String vnp_IpAddr  = Config.getIpAddress(req);
        String vnp_TmnCode = Config.vnp_TmnCode;

        Map<String, String> vnp_Params = new HashMap<>();
        vnp_Params.put("vnp_Version",   vnp_Version);
        vnp_Params.put("vnp_Command",   vnp_Command);
        vnp_Params.put("vnp_TmnCode",   vnp_TmnCode);
        vnp_Params.put("vnp_Amount",    String.valueOf(amount));
        vnp_Params.put("vnp_CurrCode",  "VND");
        vnp_Params.put("vnp_TxnRef",    vnp_TxnRef);
        vnp_Params.put("vnp_OrderInfo", "Thanh toan don hang:" + vnp_TxnRef);
        vnp_Params.put("vnp_OrderType", orderType);

        if (bankCode != null && !bankCode.isEmpty()) {
            vnp_Params.put("vnp_BankCode", bankCode);
        }

        String locate = req.getParameter("language");
        vnp_Params.put("vnp_Locale", (locate != null && !locate.isEmpty()) ? locate : "vn");

        vnp_Params.put("vnp_ReturnUrl", Config.vnp_ReturnUrl);
        vnp_Params.put("vnp_IpAddr",    vnp_IpAddr);

        Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        vnp_Params.put("vnp_CreateDate", formatter.format(cld.getTime()));

        cld.add(Calendar.MINUTE, 15);
        vnp_Params.put("vnp_ExpireDate", formatter.format(cld.getTime()));

        // Thêm generic cho List và Iterator
        List<String> fieldNames = new ArrayList<>(vnp_Params.keySet());
        Collections.sort(fieldNames);

        StringBuilder hashData = new StringBuilder();
        StringBuilder query    = new StringBuilder();

        Iterator<String> itr = fieldNames.iterator();
        while (itr.hasNext()) {
            String fieldName  = itr.next();
            String fieldValue = vnp_Params.get(fieldName);
            if (fieldValue != null && !fieldValue.isEmpty()) {
                String encodedName  = URLEncoder.encode(fieldName,  StandardCharsets.US_ASCII.toString());
                String encodedValue = URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII.toString());

                hashData.append(fieldName).append('=').append(encodedValue);
                query.append(encodedName).append('=').append(encodedValue);

                if (itr.hasNext()) {
                    hashData.append('&');
                    query.append('&');
                }
            }
        }

        String vnp_SecureHash = Config.hmacSHA512(Config.secretKey, hashData.toString());
        String paymentUrl     = Config.vnp_PayUrl + "?" + query + "&vnp_SecureHash=" + vnp_SecureHash;

        System.out.println(paymentUrl);

        resp.sendRedirect(paymentUrl);
    }
}