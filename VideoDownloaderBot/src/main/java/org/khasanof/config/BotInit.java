package org.khasanof.config;

import org.khasanof.VideoDownloader;
import org.khasanof.handlers.MainHandler;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BotInit {

    public static void init() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new VideoDownloader(new MainHandler()));
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully start!");
    }
}
