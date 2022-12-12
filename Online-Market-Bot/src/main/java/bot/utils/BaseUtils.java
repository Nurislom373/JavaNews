package bot.utils;

import bot.enums.Language;
import bot.models.User;
import org.telegram.telegrambots.meta.api.objects.Update;

public class BaseUtils {
    public static String translate(String lang, Language text) {
        for (Language value : Language.values()) {
            if (value.equals(text)) {
                if (lang.equalsIgnoreCase("uz")) return value.getUz();
                if (lang.equalsIgnoreCase("ru")) return value.getRu();
                if (lang.equalsIgnoreCase("en")) return value.getEn();
            }
        }
        return "";
    }

    public static void initUser(Update update, User user) {
        user.setChatId(update.getMessage().getChatId());
        user.setUsername(update.getMessage().getFrom().getUserName());
        user.setFirstName(update.getMessage().getFrom().getFirstName());
        user.setLastName(update.getMessage().getFrom().getLastName());
    }
}
