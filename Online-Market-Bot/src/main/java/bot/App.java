package bot;

import bot.config.BotInit;
import bot.config.Migration;

public class App {
    public static void main(String[] args) {
//        Migration.init();
        BotInit.botInit();
    }
}
