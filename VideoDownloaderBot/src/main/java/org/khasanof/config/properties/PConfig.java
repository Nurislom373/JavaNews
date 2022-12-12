package org.khasanof.config.properties;

import java.io.FileReader;
import java.util.Properties;

public class PConfig {
    private static Properties p;

    static {
        try (FileReader fileReader = new FileReader("src/main/resources/app/app.properties")) {
            p = new Properties();
            p.load(fileReader);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String get(String key) {
        return p.getProperty(key);
    }
}
