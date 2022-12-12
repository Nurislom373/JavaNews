package org.khasanof.utils.baseUtils;

public class ValidateUtils {
    public static void validateURL(String url){
        if (url.startsWith("www.") || url.startsWith("instagram.com")){
            throw new IllegalArgumentException("URL must start with https://");
        }

        if (!(url.startsWith("https://") || url.startsWith("http://"))){
            throw new IllegalArgumentException("Invalid instagram URL");
        }

        if (!url.contains("instagram.com/p/") && !url.contains("instagram.com/tv/")){
            throw new IllegalArgumentException("Invalid instagram URL");
        }

        if (url.length() <=0){
            throw new IllegalArgumentException("URL parameter cannot be empty");
        }

    }
}
