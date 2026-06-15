package utils;

import java.sql.Timestamp;
import java.time.Instant;

// Chuyển chuỗi thời gian dạng ISO-8601 sang Timestamp để lưu/xử lý với database.
// Ví dụ input: "2024-05-22T23:59:59Z"

public class TransDate {
    public static Timestamp formate(String timeString) {
        return Timestamp.from(Instant.parse(timeString));
    }

    public static void main(String[] args) {
        System.out.println(formate("2024-05-22T23:59:59Z"));
    }
}
