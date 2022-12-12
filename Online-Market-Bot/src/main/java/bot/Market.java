package bot;

import bot.config.PConfig;
import bot.handlers.MainHandler;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.Update;

public class Market extends TelegramLongPollingBot {
    public static final Market instance = new Market();
    private static final MainHandler MAIN_HANDLER = MainHandler.instance;

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
        MAIN_HANDLER.handler(update);
    }

    public void executeMessage(BotApiMethod<?> message) {
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void executePhoto(SendPhoto message) {
        try {
            execute(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Market getInstance() {
        return instance;
    }
}
