package bot.keyboards;

import bot.enums.Language;
import bot.handlers.message.MessageHandler;
import bot.utils.BaseUtils;
import bot.utils.Emojis;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

public class ReplyKeyboard {
    private static final ReplyKeyboardMarkup REPLY_KEYBOARD_MARKUP = new ReplyKeyboardMarkup();

    public static ReplyKeyboardMarkup enterMenu() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton menu = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.MENU) + " " + Emojis.MENU);
        KeyboardButton discount = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.DISCOUNT) + " " + Emojis.DISCOUNT);
        KeyboardButton settings = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.SETTINGS) + " " + Emojis.SETTINGS);
        row1.add(menu);
        row2.add(discount);
        row2.add(settings);
        REPLY_KEYBOARD_MARKUP.setKeyboard(List.of(row1, row2));
        return REPLY_KEYBOARD_MARKUP;
    }

    public static ReplyKeyboardMarkup menuMenu() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton toOrder = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.TO_ORDER) + " " + Emojis.TO_ORDER);
        KeyboardButton myOrder = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.MY_ORDER) + " " + Emojis.MY_ORDER);
        KeyboardButton basket = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.BASKET) + " " + Emojis.BASKET);
        KeyboardButton back = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.BACK) + " " + Emojis.BACK);
        row1.add(toOrder);
        row2.add(myOrder);
        row2.add(basket);
        row3.add(back);
        REPLY_KEYBOARD_MARKUP.setKeyboard(List.of(row1, row2, row3));
        return REPLY_KEYBOARD_MARKUP;
    }

    public static ReplyKeyboardMarkup settings() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton changeLanguage = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.CHANGE_LANGUAGE) + " " + Emojis.LANGUAGE);
        KeyboardButton card = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.CARD) + " " + Emojis.CARD);
        KeyboardButton phone = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.PHONE) + " " + Emojis.PHONE);
        KeyboardButton back = new KeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.BACK) + " " + Emojis.BACK);
        row1.add(changeLanguage);
        row2.add(card);
        row2.add(phone);
        row3.add(back);
        REPLY_KEYBOARD_MARKUP.setKeyboard(List.of(row1, row2, row3));
        return REPLY_KEYBOARD_MARKUP;
    }
}
