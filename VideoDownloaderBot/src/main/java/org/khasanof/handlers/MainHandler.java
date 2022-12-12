package org.khasanof.handlers;

import org.khasanof.VideoDownloader;
import org.khasanof.handlers.callBack.CallbackHandler;
import org.khasanof.handlers.message.MessageHandler;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MainHandler {
    private final MessageHandler MESSAGE_HANDLER;
    private final CallbackHandler CALL_BACK_HANDLER;

    public MainHandler() {
        this.MESSAGE_HANDLER = new MessageHandler();
        this.CALL_BACK_HANDLER = new CallbackHandler();
    }

    public void handler(Update update, VideoDownloader bot) {
        if (update.hasMessage()) {
            MESSAGE_HANDLER.process(update, bot);
        } else if (update.hasCallbackQuery()) {
            CALL_BACK_HANDLER.process(update, bot);
        }
    }
}
