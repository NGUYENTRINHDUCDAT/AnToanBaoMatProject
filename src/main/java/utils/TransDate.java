package utils;

import java.sql.Timestamp;
import java.time.Instant;

public class TransDate {
    public static Timestamp formate(String timeString) {
        return Timestamp.from(Instant.parse(timeString));
    }

    public static void main(String[] args) {
        System.out.println(formate("2024-05-22T23:59:59Z"));
    }
}
