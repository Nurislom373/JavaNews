package org.khasanof.service.instagram;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import org.asynchttpclient.AsyncHttpClient;
import org.asynchttpclient.DefaultAsyncHttpClient;
import org.khasanof.entity.instagram.InstagramEntity;
import org.khasanof.utils.baseUtils.BaseUtils;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.concurrent.ExecutionException;

public class InstagramService {

    @SneakyThrows
    public static InstagramEntity load(String url) {

        Gson gson = new Gson();
        Type type = new TypeToken<InstagramEntity>() {
        }.getType();

//        ValidateUtils.validateURL(url);

//        OkHttpClient client = new OkHttpClient();
//
//        RequestBody body = new FormBody.Builder()
//                .add("url", url)
//                .build();
//
//        Request request = new Request.Builder()
//                .url("https://instagram-video-or-images-downloader.p.rapidapi.com/")
//                .post(body)
//                .addHeader("content-type", "application/x-www-form-urlencoded")
//                .addHeader("X-RapidAPI-Key", "45c4d31191msh5d479e8a37e217ep10c2e5jsn1c502dcfecb7")
//                .addHeader("X-RapidAPI-Host", "instagram-video-or-images-downloader.p.rapidapi.com")
//                .build();
//        Response response = client.newCall(request).execute();

        String entity = asyncInstagramEntity(url);

        InstagramEntity instagram = gson.fromJson(entity, type);
        System.out.println("instagram = " + instagram);
        return instagram;
    }

    private static String asyncInstagramEntity(String url) throws IOException, ExecutionException, InterruptedException {
        AsyncHttpClient client = new DefaultAsyncHttpClient();
        org.asynchttpclient.Response response = client.prepare("POST", "https://instagram-video-or-images-downloader.p.rapidapi.com/")
                .setHeader("content-type", "application/x-www-form-urlencoded")
                .setHeader("X-RapidAPI-Key", "45c4d31191msh5d479e8a37e217ep10c2e5jsn1c502dcfecb7")
                .setHeader("X-RapidAPI-Host", "instagram-video-or-images-downloader.p.rapidapi.com")
                .setBody("url=" + BaseUtils.changeUrlAddUrlCharacters(url))
                .execute()
                .toCompletableFuture().get();
        client.close();
        return response.getResponseBody();
    }

}
