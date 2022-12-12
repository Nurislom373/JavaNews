package org.khasanof.keyboards.reply;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ReplyKeyboard {
    private static final ReplyKeyboardMarkup REPLY_KEYBOARD_MARKUP = new ReplyKeyboardMarkup();

    public static ReplyKeyboardMarkup enterMenu() {
        REPLY_KEYBOARD_MARKUP.setResizeKeyboard(true);
        KeyboardRow row1 = new KeyboardRow();
        KeyboardRow row2 = new KeyboardRow();
        KeyboardRow row3 = new KeyboardRow();
        KeyboardButton Instagram = new KeyboardButton("Instagram");
        KeyboardButton YouTube = new KeyboardButton("YouTube");
        KeyboardButton Facebook = new KeyboardButton("Facebook");
        KeyboardButton TikTok = new KeyboardButton("TikTok");
        row1.add(YouTube);
        row1.add(Instagram);
        row2.add(Facebook);
        row2.add(TikTok);
        List<KeyboardRow> keyboardRows = new ArrayList<>();
        keyboardRows.add(row1);
        keyboardRows.add(row2);
        REPLY_KEYBOARD_MARKUP.setKeyboard(keyboardRows);
        return REPLY_KEYBOARD_MARKUP;
    }
}
