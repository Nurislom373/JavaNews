package org.khasanof;

import io.minio.*;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import static io.minio.ObjectWriteArgs.MIN_MULTIPART_SIZE;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 1/30/2024 5:05 PM
 */
public class MinioUploader {

    private static final String FOLDER_NAME = "java.folder";

    @SneakyThrows
    public static void upload(File file) {
        MinioClient minioClient = MinioClientBuilder.build();

        if (!minioClient.bucketExists(getExistBucket())) {
            minioClient.makeBucket(getJavaFolderBucket());
        }
        minioClient.putObject(createObject(file));
    }

    @SneakyThrows
    public static InputStream getObject(String name) {
        MinioClient minioClient = MinioClientBuilder.build();
        return minioClient.getObject(getObjectArgs(name));
    }

    private static GetObjectArgs getObjectArgs(String name) {
        return GetObjectArgs.builder()
                .bucket(FOLDER_NAME)
                .object(name)
                .build();
    }

    private static PutObjectArgs createObject(File file) throws IOException {
        return PutObjectArgs.builder()
                .bucket(FOLDER_NAME)
                .object(file.getName())
                .stream(Files.newInputStream(file.toPath()), file.length(), MIN_MULTIPART_SIZE)
                .build();
    }

    private static BucketExistsArgs getExistBucket() {
        return BucketExistsArgs.builder()
                .bucket(FOLDER_NAME)
                .build();
    }

    private static MakeBucketArgs getJavaFolderBucket() {
        return MakeBucketArgs.builder()
                .bucket(FOLDER_NAME)
                .build();
    }
}
