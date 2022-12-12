package org.khasanof;

import org.khasanof.config.properties.PConfig;
import org.khasanof.handlers.MainHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.Update;


public class VideoDownloader extends TelegramLongPollingBot {
    private final MainHandler mainHandler;

    public VideoDownloader(MainHandler mainHandler) {
        this.mainHandler = mainHandler;
    }

    @Override
    public String getBotUsername() {
        return PConfig.get("bot.username");
    }

    @Override
    public String getBotToken() {
        return PConfig.get("bot.token");
    }

    @Override
    public void onUpdateReceived(Update update) {
        mainHandler.handler(update, this);
    }

    public void executeMessage(BotApiMethod<?> message) {
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executePhoto(SendPhoto photo) {
        try {
            execute(photo);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executeVideo(SendVideo video) {
        try {
            execute(video);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
