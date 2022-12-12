package org.khasanof.service.tiktok;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import lombok.SneakyThrows;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.khasanof.entity.tiktok.TikTokEntity;
import org.khasanof.utils.baseUtils.BaseUtils;

import java.lang.reflect.Type;

public class TikTokService {

    @SneakyThrows
    public static TikTokEntity load(String url) {
        Gson gson = new Gson();
        Type type = new TypeToken<TikTokEntity>() {
        }.getType();

        OkHttpClient client = new OkHttpClient();

        String urlCharacters = BaseUtils.changeUrlAddUrlCharacters(url);

        Request request = new Request.Builder()
                .url("https://tiktok-downloader-download-tiktok-videos-without-watermark.p.rapidapi.com/index?url=" + urlCharacters)
                .get()
                .addHeader("X-RapidAPI-Key", "45c4d31191msh5d479e8a37e217ep10c2e5jsn1c502dcfecb7")
                .addHeader("X-RapidAPI-Host", "tiktok-downloader-download-tiktok-videos-without-watermark.p.rapidapi.com")
                .build();

        Response response = client.newCall(request).execute();
        TikTokEntity tikTok = gson.fromJson(response.body().string(), type);
        return tikTok;
    }
}
