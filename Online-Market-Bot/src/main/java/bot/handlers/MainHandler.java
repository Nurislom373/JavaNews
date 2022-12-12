package bot.handlers;

import bot.handlers.callBack.CallBackHandler;
import bot.handlers.message.MessageHandler;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MainHandler {
    public static final MainHandler instance = new MainHandler();
    private static final MessageHandler MESSAGE_HANDLER = MessageHandler.instance;
    private static final CallBackHandler CALL_BACK_HANDLER = CallBackHandler.instance;

    public void handler(Update update) {
        if (update.hasMessage()) {
            MESSAGE_HANDLER.process(update);
        } else if (update.hasCallbackQuery()) {
            CALL_BACK_HANDLER.process(update);
        }
    }

    public static MainHandler getInstance() {
        return instance;
    }
}
