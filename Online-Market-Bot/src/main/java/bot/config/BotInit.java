package bot.config;

import bot.Market;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class BotInit {
    public static void botInit() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new Market());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
        System.out.println("Successfully start market bot!");
    }
}
