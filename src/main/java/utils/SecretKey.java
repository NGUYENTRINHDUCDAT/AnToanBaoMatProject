package utils;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

public class SecretKey {

    public static String getSecretKeyAsBase64() {
        try {
            // Đọc các giá trị từ biến môi trường
            String keystorePath = System.getenv("KEYSTORE_PATH");
            String keystorePassword = System.getenv("KEYSTORE_PASSWORD");
            String secretKeyAlias = System.getenv("SECRET_KEY_ALIAS");
            String secretKeyPassword = System.getenv("SECRET_KEY_PASSWORD");

            // Kiểm tra các biến môi trường bắt buộc
            if (keystorePath == null || keystorePassword == null || secretKeyAlias == null || secretKeyPassword == null) {
                throw new IllegalStateException("Thiếu biến môi trường. Vui lòng thiết lập: KEYSTORE_PATH, KEYSTORE_PASSWORD, SECRET_KEY_ALIAS, SECRET_KEY_PASSWORD.");
            }

            // Tải keystore từ file
            try (FileInputStream fis = new FileInputStream(keystorePath)) {
                KeyStore keystore = KeyStore.getInstance("JCEKS");
                keystore.load(fis, keystorePassword.toCharArray());

                // Lấy secret key từ keystore
                Key secretKey = keystore.getKey(secretKeyAlias, secretKeyPassword.toCharArray());

                if (secretKey == null) {
                    throw new RuntimeException("Không tìm thấy secret key với alias: " + secretKeyAlias);
                }

                // Mã hóa secret key dưới dạng Base64
                return Base64.getEncoder().encodeToString(secretKey.getEncoded());
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy secret key: " + e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
        // Thử nghiệm lấy secret key
        try {
            String secretKeyBase64 = getSecretKeyAsBase64();
            System.out.println("Secret Key (Base64): " + secretKeyBase64);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
