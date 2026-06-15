package utils;

import java.text.NumberFormat;
import java.util.Locale;

public class CurrencyUtils {

    // Định dạng số tiền theo kiểu Việt Nam.
    // Ví dụ: 150000 -> 150.000 ₫
    public static String formatVnd(double amount) {
        NumberFormat formatter = NumberFormat.getCurrencyInstance(new Locale("vi", "VN"));
        return formatter.format(amount);
    }
}
