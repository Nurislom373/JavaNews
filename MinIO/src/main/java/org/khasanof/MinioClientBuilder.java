package org.khasanof;

import io.minio.MinioClient;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/30/2024 5:02 PM
 */
public class MinioClientBuilder {

    private static final String ACCESS_KEY = "minio_user";
    private static final String SECRET_KEY = "minio_password";

    public static MinioClient build() {
        return MinioClient.builder()
                .endpoint("http://127.0.0.1:9000")
                .credentials(ACCESS_KEY, SECRET_KEY)
                .build();
    }
}
