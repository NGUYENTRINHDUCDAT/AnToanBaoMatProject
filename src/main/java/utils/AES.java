package utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;

public class AES {

    // Sinh khóa AES ngẫu nhiên
    public static String generateAESKey() throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(256);
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // Mã hóa tệp và lưu khóa AES vào header
    public static void encryptFile(File inputFile, File outputFile) throws Exception {
        String aesKey = generateAESKey();
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding"); // explicit để tránh warning
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            fos.write((aesKey + "\n").getBytes());

            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) fos.write(output);
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) fos.write(outputBytes);
        }
    }

    // Giải mã tệp với khóa AES từ header
    public static void decryptFile(File inputFile, File outputFile) throws Exception {

        // Đọc toàn bộ file bằng một stream duy nhất
        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            // Đọc header (key) thủ công từ binary stream
            ByteArrayOutputStream headerBuf = new ByteArrayOutputStream();
            int b;
            while ((b = fis.read()) != -1) {
                if (b == '\n') break;
                headerBuf.write(b);
            }

            String aesKey = headerBuf.toString();
            SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Giải mã phần còn lại
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) fos.write(output);
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) fos.write(outputBytes);
        }
    }
}