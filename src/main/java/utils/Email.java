package utils;
import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import model.Customer;
import model.Order;


public class Email {
    // Email: tungletest1.email@gmail.com
    // Password: nebeekfipcstxcox
    static final String from = "21130312@st.hcmuaf.edu.vn";
    static final String password = "pqya jyfm lvil ptqd";

    public static boolean sendEmail(String to, String tieuDe, String noiDung) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));

            // Tiêu đề email
            msg.setSubject(tieuDe);

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))

            // Nội dung

            MimeMultipart multipart = new MimeMultipart();

            // Phần văn bản của email


            // Phần HTML của email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h4>Xin chào bạn </h4>" + "\r\n" + "<p> " + noiDung + "</p>" +  " </body></html>";
            htmlPart.setContent(htmlContent, "text/HTML; charset=UTF-8");

            // Thêm các phần vào MimeMultipart
            multipart.addBodyPart(htmlPart);


            msg.setContent(multipart, "text/HTML; charset=UTF-8");

            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendEmaiQuenMatKhau(String title,Customer customer, String resetCode) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmail(), false));

            // Tiêu đề email
            msg.setSubject(title);

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))

            // Nội dung

            MimeMultipart multipart = new MimeMultipart();

            // Phần văn bản của email


            // Phần HTML của email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h4>Xin chào " + customer.getFullName() + " </h4>" + "\r\n" + "<p> " + " Mã xác nhận là " + resetCode + "." + "</p>" + "\r\n" + "Chúc bạn một ngày vui vẻ," +  " </body></html>";
            htmlPart.setContent(htmlContent, "text/HTML; charset=UTF-8");

            // Thêm các phần vào MimeMultipart
            multipart.addBodyPart(htmlPart);


            msg.setContent(multipart, "text/HTML; charset=UTF-8");

            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }

    //	public static boolean sendEmailXacNhanDonHang(String to, String noiDung) {
//		// Properties : khai báo các thuộc tính
//		Properties props = new Properties();
//		props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
//		props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
//		props.put("mail.smtp.auth", "true");
//		props.put("mail.smtp.starttls.enable", "true");
//
//		// create Authenticator
//		Authenticator auth = new Authenticator() {
//			@Override
//			protected PasswordAuthentication getPasswordAuthentication() {
//				// TODO Auto-generated method stub
//				return new PasswordAuthentication(from, password);
//			}
//		};
//
//		// Phiên làm việc
//		Session session = Session.getInstance(props, auth);
//
//		// Tạo một tin nhắn
//		MimeMessage msg = new MimeMessage(session);
//
//		try {
//			// Kiểu nội dung
//			msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
//
//			// Người gửi
//			msg.setFrom(from);
//
//			// Người nhận
//			msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to, false));
//
//			// Tiêu đề email
//			msg.setSubject("Xác Nhận Mua Hàng Thành Công");
//
//			// Quy đinh ngày gửi
//			msg.setSentDate(new Date());
//
//			// Quy định email nhận phản hồi
//			// msg.setReplyTo(InternetAddress.parse(from, false))
//
//			// Nội dung
//			msg.setContent(noiDung, "text/HTML; charset=UTF-8");
//
//			// Gửi email
//			Transport.send(msg);
//			System.out.println("Gửi email thành công");
//			return true;
//		} catch (Exception e) {
//			System.out.println("Gặp lỗi trong quá trình gửi email");
//			e.printStackTrace();
//			return false;
//		}
//	}
//
    public static boolean sendEmailXacNhanDonHang(Order order) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                // TODO Auto-generated method stub
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(order.getCustomer().getEmail(), false));

            // Tiêu đề email
            msg.setSubject("Xác Nhận Mua Hàng Thành Công");

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Quy định email nhận phản hồi
            // msg.setReplyTo(InternetAddress.parse(from, false))

            // Nội dung
            MimeMultipart multipart = new MimeMultipart();

            // Phần văn bản của email


            // Phần HTML của email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h4>Xin chào " + order.getCustomer().getFullName() + " </h4>" + "\r\n" + "<p> " + "Cảm ơn bạn đã mua hàng bên chúng tôi"+ "." + "\n" + StringFilter.xacThucDonHang(order) + "</p>" + "\r\n" + "Chú bạn một ngày vui vẻ," +  " </body></html>";
            htmlPart.setContent(htmlContent, "text/HTML; charset=UTF-8");

            // Thêm các phần vào MimeMultipart
            multipart.addBodyPart(htmlPart);


            msg.setContent(multipart, "text/HTML; charset=UTF-8");
            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }
    public static boolean sendEmailCanhBaoDonHang(Order order) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // Tạo Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(order.getCustomer().getEmail());

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(order.getCustomer().getEmail(), false));

            // Tiêu đề email
            msg.setSubject("Cảnh Báo: Đơn Hàng Bị Chỉnh Sửa Trái Phép");

            // Quy định ngày gửi
            msg.setSentDate(new Date());

            // Nội dung email
            MimeMultipart multipart = new MimeMultipart();

            // Phần HTML của email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body>"
                    + "<h4>Xin chào " + order.getCustomer().getFullName() + ",</h4>"
                    + "<p>Chúng tôi phát hiện đơn hàng của bạn đã bị chỉnh sửa trái phép. "
                    + "Nếu bạn không thực hiện hành động này, vui lòng liên hệ với chúng tôi ngay lập tức để xử lý vấn đề.</p>"
                    + "<p>Thông tin đơn hàng:</p>"
                    + "<ul>"
                    + "<li><strong>Mã đơn hàng:</strong> " + order.getId() + "</li>"
                    + "<li><strong>Ngày đặt:</strong> " + order.getDate().toString() + "</li>"
                    + "<li><strong>Trạng thái:</strong> Đã bị chỉnh sửa</li>"
                    + "</ul>"
                    + "<p>Xin lỗi vì sự bất tiện này và cảm ơn bạn đã sử dụng dịch vụ của chúng tôi.</p>"
                    + "<p>Trân trọng,</p>"
                    + "<p>Đội ngũ hỗ trợ khách hàng</p>"
                    + "</body></html>";
            htmlPart.setContent(htmlContent, "text/HTML; charset=UTF-8");

            // Thêm phần HTML vào MimeMultipart
            multipart.addBodyPart(htmlPart);

            // Đặt nội dung cho email
            msg.setContent(multipart);

            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email cảnh báo thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email cảnh báo");
            e.printStackTrace();
            return false;
        }
    }

    public static boolean sendEmailPrivateKey(Customer customer, File privateKeyFile) {
        // Properties : khai báo các thuộc tính
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); // SMTP HOST
        props.put("mail.smtp.port", "587"); // TLS 587 SSL 465
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        // create Authenticator
        Authenticator auth = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, password);
            }
        };

        // Phiên làm việc
        Session session = Session.getInstance(props, auth);

        // Tạo một tin nhắn
        MimeMessage msg = new MimeMessage(session);

        try {
            // Kiểu nội dung
            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");

            // Người gửi
            msg.setFrom(from);

            // Người nhận
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(customer.getEmail(), false));

            // Tiêu đề email
            msg.setSubject("Key sử dụng cho hệ thống");

            // Quy đinh ngày gửi
            msg.setSentDate(new Date());

            // Nội dung
            MimeMultipart multipart = new MimeMultipart();

            // Phần HTML của email
            MimeBodyPart htmlPart = new MimeBodyPart();
            String htmlContent = "<html><body><h4>Xin chào "  +customer.getFullName()+ " </h4>" + "\r\n" +
                    "<p> Đây là private key của bạn, hãy giữ nó cẩn thận." + "\n" + "</p>" +
                    "\r\nChúc bạn một ngày vui vẻ," + "</body></html>";
            htmlPart.setContent(htmlContent, "text/HTML; charset=UTF-8");

            // Thêm phần HTML vào MimeMultipart
            multipart.addBodyPart(htmlPart);

            // Thêm tệp đính kèm
            MimeBodyPart attachmentPart = new MimeBodyPart();
            attachmentPart.attachFile(privateKeyFile); // Gửi tệp private_key
            attachmentPart.setFileName("private_key"); // Tên hiển thị của tệp
            multipart.addBodyPart(attachmentPart);

            // Đặt nội dung email
            msg.setContent(multipart);

            // Gửi email
            Transport.send(msg);
            System.out.println("Gửi email thành công");
            return true;
        } catch (Exception e) {
            System.out.println("Gặp lỗi trong quá trình gửi email");
            e.printStackTrace();
            return false;
        }
    }


    public static void main(String[] args) {


    }

}