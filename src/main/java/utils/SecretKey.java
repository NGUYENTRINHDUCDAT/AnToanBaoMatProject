package utils;

import java.io.FileInputStream;
import java.security.Key;
import java.security.KeyStore;
import java.util.Base64;

public class SecretKey {
    private static final String KEYSTORE_PATH_ENV = "KEYSTORE_PATH";
    private static final String KEYSTORE_PASSWORD_ENV = "KEYSTORE_PASSWORD";
    private static final String SECRET_KEY_ALIAS_ENV = "SECRET_KEY_ALIAS";
    private static final String SECRET_KEY_PASSWORD_ENV = "SECRET_KEY_PASSWORD";
    private static final String KEYSTORE_TYPE = "JCEKS";

    public static String getSecretKeyAsBase64() {
        try {
            // Đọc thông tin keystore từ biến môi trường để tránh hard-code dữ liệu nhạy cảm trong source code.
            String keystorePath = System.getenv(KEYSTORE_PATH_ENV);
            String keystorePassword = System.getenv(KEYSTORE_PASSWORD_ENV);
            String secretKeyAlias = System.getenv(SECRET_KEY_ALIAS_ENV);
            String secretKeyPassword = System.getenv(SECRET_KEY_PASSWORD_ENV);

            validateEnvironmentVariables(
                    keystorePath,
                    keystorePassword,
                    secretKeyAlias,
                    secretKeyPassword
            );

            // Tải keystore từ file và tự động đóng FileInputStream sau khi xử lý xong.
            try (FileInputStream fis = new FileInputStream(keystorePath)) {
                KeyStore keystore = KeyStore.getInstance(KEYSTORE_TYPE);
                keystore.load(fis, keystorePassword.toCharArray());

                // Lấy secret key theo alias đã cấu hình trong biến môi trường.
                Key secretKey = keystore.getKey(secretKeyAlias, secretKeyPassword.toCharArray());

                if (secretKey == null) {
                    throw new IllegalStateException("Không tìm thấy secret key với alias: " + secretKeyAlias);
                }

                // Chuyển secret key sang Base64 để dễ sử dụng trong quá trình tạo hash/xác thực dữ liệu.
                return Base64.getEncoder().encodeToString(secretKey.getEncoded());
            }
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy secret key: " + e.getMessage(), e);
        }
    }

    private static void validateEnvironmentVariables(
            String keystorePath,
            String keystorePassword,
            String secretKeyAlias,
            String secretKeyPassword
    ) {
        if (isBlank(keystorePath)
                || isBlank(keystorePassword)
                || isBlank(secretKeyAlias)
                || isBlank(secretKeyPassword)) {
            throw new IllegalStateException(
                    "Thiếu biến môi trường. Vui lòng thiết lập: "
                            + KEYSTORE_PATH_ENV + ", "
                            + KEYSTORE_PASSWORD_ENV + ", "
                            + SECRET_KEY_ALIAS_ENV + ", "
                            + SECRET_KEY_PASSWORD_ENV + "."
            );
        }
    }

    private static boolean isBlank(String value) {
        return value == null || value.trim().isEmpty();
    }
}