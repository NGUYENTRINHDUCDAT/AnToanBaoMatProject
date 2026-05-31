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
        keyGen.init(256); // Độ dài khóa 256-bit
        SecretKey secretKey = keyGen.generateKey();
        return Base64.getEncoder().encodeToString(secretKey.getEncoded());
    }

    // Mã hóa tệp và lưu khóa AES vào header
    public static void encryptFile(File inputFile, File outputFile) throws Exception {
        // Tạo khóa AES mới
        String aesKey = generateAESKey();
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);

        try (FileInputStream fis = new FileInputStream(inputFile);
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            // Ghi khóa AES vào header (dưới dạng Base64)
            fos.write((aesKey + "\n").getBytes());

            // Mã hóa nội dung tệp
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = fis.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    fos.write(output);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                fos.write(outputBytes);
            }
        }
    }

    // Giải mã tệp với khóa AES từ header
    public static void decryptFile(File inputFile, File outputFile) throws Exception {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
             FileOutputStream fos = new FileOutputStream(outputFile)) {

            // Đọc khóa AES từ header
            String aesKey = br.readLine();
            SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(aesKey), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Đọc và giải mã nội dung còn lại
            byte[] buffer = new byte[1024];
            int bytesRead;
            InputStream is = new FileInputStream(inputFile);
            // Skip header (AES Key line)
            is.skip(aesKey.length() + 1);

            while ((bytesRead = is.read(buffer)) != -1) {
                byte[] output = cipher.update(buffer, 0, bytesRead);
                if (output != null) {
                    fos.write(output);
                }
            }
            byte[] outputBytes = cipher.doFinal();
            if (outputBytes != null) {
                fos.write(outputBytes);
            }
        }
    }
}
