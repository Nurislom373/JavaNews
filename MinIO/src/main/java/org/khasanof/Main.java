package org.khasanof;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class Main {

    public static void main(String[] args) throws IOException {
        MinioUploader.upload(new File("D:\\Nurislom\\Projects\\JavaNews\\MinIO\\src\\main\\resources\\vivoz_musora.png"));

        File file = new File("D:\\Nurislom\\Projects\\JavaNews\\MinIO\\src\\main\\resources\\vivoz.png");
        file.createNewFile();

        InputStream inputStream = MinioUploader.getObject("vivoz_musora.png");
        Files.copy(inputStream, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }

}