package bot.keyboards;

import bot.enums.Language;
import bot.handlers.callBack.CallBackHandler;
import bot.handlers.message.MessageHandler;
import bot.repository.product.ProductRp;
import bot.utils.BaseUtils;
import bot.utils.Emojis;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InlineKeyboard {
    private static final InlineKeyboardMarkup INLINE_KEYBOARD_MARKUP = new InlineKeyboardMarkup();

    public static InlineKeyboardMarkup language() {
        InlineKeyboardButton uz = new InlineKeyboardButton("Uzbek " + Emojis.UZ);
        uz.setCallbackData("UZ");

        InlineKeyboardButton ru = new InlineKeyboardButton("Russia " + Emojis.RU);
        ru.setCallbackData("RU");

        InlineKeyboardButton en = new InlineKeyboardButton("English " + Emojis.EN);
        en.setCallbackData("EN");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(en);
        row1.add(ru);

        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row2.add(uz);

        INLINE_KEYBOARD_MARKUP.setKeyboard(Arrays.asList(row1, row2));
        return INLINE_KEYBOARD_MARKUP;
    }

    public static InlineKeyboardMarkup basketButtons() {
        InlineKeyboardButton buy = new InlineKeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.BUY) + " " + Emojis.BASKET);
        buy.setCallbackData("BUY");

        InlineKeyboardButton change = new InlineKeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.CHANGE) + " " + Emojis.EDIT);
        change.setCallbackData("CHANGE");

        InlineKeyboardButton delete = new InlineKeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.CANCEL) + " " + Emojis.BTN_DELETE);
        delete.setCallbackData("DELETE");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        row1.add(change);
        row1.add(delete);
        row2.add(buy);

        INLINE_KEYBOARD_MARKUP.setKeyboard(Arrays.asList(row1, row2));
        return INLINE_KEYBOARD_MARKUP;
    }

    public static InlineKeyboardMarkup photo() {
        InlineKeyboardButton minus = new InlineKeyboardButton(Emojis.MINUS);
        minus.setCallbackData("MINUS");

        InlineKeyboardButton plus = new InlineKeyboardButton(Emojis.PLUS);
        plus.setCallbackData("PLUS");

        InlineKeyboardButton count = new InlineKeyboardButton(CallBackHandler.count + "");
        count.setCallbackData(CallBackHandler.count + "");

        InlineKeyboardButton add = new InlineKeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.ADD) + " " + Emojis.BASKET);
        add.setCallbackData("ADD");

        InlineKeyboardButton delete = new InlineKeyboardButton(BaseUtils.translate(MessageHandler.user.getLanguage(), Language.CANCEL) + " " + Emojis.BTN_DELETE);
        delete.setCallbackData("DELETE");

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        row1.add(minus);
        row1.add(count);
        row1.add(plus);
        row2.add(add);
        row3.add(delete);



        INLINE_KEYBOARD_MARKUP.setKeyboard(Arrays.asList(row1, row2, row3));
        return INLINE_KEYBOARD_MARKUP;
    }

    public static InlineKeyboardMarkup nextProductButtons(int offset) {
        InlineKeyboardButton prev = new InlineKeyboardButton(Emojis.BTN_PREV);
        prev.setCallbackData("PREV");

        InlineKeyboardButton next = new InlineKeyboardButton(Emojis.BTN_NEXT);
        next.setCallbackData("NEXT");

        InlineKeyboardButton delete = new InlineKeyboardButton(Emojis.BTN_DELETE);
        delete.setCallbackData("DELETE");

        int count = ProductRp.instance.getAll().size() - offset;
        System.out.println(count);
        List<InlineKeyboardButton> buttons = setCountButtons(count, String.valueOf(CallBackHandler.offset));
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        if (buttons.size() == 5) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row2.add(buttons.get(3));
            row2.add(buttons.get(4));
        } else if (buttons.size() == 10) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row3.add(buttons.get(3));
            row3.add(buttons.get(4));
            row2.add(buttons.get(5));
            row2.add(buttons.get(6));
            row2.add(buttons.get(7));
            row2.add(buttons.get(8));
            row2.add(buttons.get(9));
        } else if (buttons.size() == 6) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row2.add(buttons.get(3));
            row2.add(buttons.get(4));
            row2.add(buttons.get(5));
        } else if (buttons.size() == 7) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row3.add(buttons.get(3));
            row2.add(buttons.get(4));
            row2.add(buttons.get(5));
            row2.add(buttons.get(6));
        } else if (buttons.size() == 8) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row3.add(buttons.get(3));
            row2.add(buttons.get(4));
            row2.add(buttons.get(5));
            row2.add(buttons.get(6));
            row2.add(buttons.get(7));
        } else if (buttons.size() == 9) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row3.add(buttons.get(3));
            row3.add(buttons.get(4));
            row2.add(buttons.get(5));
            row2.add(buttons.get(6));
            row2.add(buttons.get(7));
            row2.add(buttons.get(8));
        } else if (buttons.size() == 1) {
            row3.add(buttons.get(0));
        } else if (buttons.size() == 2) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
        } else if (buttons.size() == 3) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
        } else if (buttons.size() == 4) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row3.add(buttons.get(3));
        }

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(prev);
        row1.add(delete);
        row1.add(next);

        INLINE_KEYBOARD_MARKUP.setKeyboard(Arrays.asList(row3, row2, row1));
        return INLINE_KEYBOARD_MARKUP;
    }

    public static InlineKeyboardMarkup productButtons() {
        InlineKeyboardButton prev = new InlineKeyboardButton(Emojis.BTN_PREV);
        prev.setCallbackData("PREV");

        InlineKeyboardButton next = new InlineKeyboardButton(Emojis.BTN_NEXT);
        next.setCallbackData("NEXT");

        InlineKeyboardButton delete = new InlineKeyboardButton(Emojis.BTN_DELETE);
        delete.setCallbackData("DELETE");

        int count = ProductRp.instance.getAll().size();
        List<InlineKeyboardButton> buttons = getCountButtons(count);
        List<InlineKeyboardButton> row2 = new ArrayList<>();
        List<InlineKeyboardButton> row3 = new ArrayList<>();
        if (buttons.size() == 5) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row2.add(buttons.get(3));
            row2.add(buttons.get(4));
        } else if (buttons.size() == 10) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row3.add(buttons.get(3));
            row3.add(buttons.get(4));
            row2.add(buttons.get(5));
            row2.add(buttons.get(6));
            row2.add(buttons.get(7));
            row2.add(buttons.get(8));
            row2.add(buttons.get(9));
        } else if (buttons.size() == 6) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row2.add(buttons.get(3));
            row2.add(buttons.get(4));
            row2.add(buttons.get(5));
        } else if (buttons.size() == 7) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row3.add(buttons.get(3));
            row2.add(buttons.get(4));
            row2.add(buttons.get(5));
            row2.add(buttons.get(6));
        } else if (buttons.size() == 8) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row3.add(buttons.get(3));
            row2.add(buttons.get(4));
            row2.add(buttons.get(5));
            row2.add(buttons.get(6));
            row2.add(buttons.get(7));
        } else if (buttons.size() == 9) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row3.add(buttons.get(3));
            row3.add(buttons.get(4));
            row2.add(buttons.get(5));
            row2.add(buttons.get(6));
            row2.add(buttons.get(7));
            row2.add(buttons.get(8));
        } else if (buttons.size() == 1) {
            row3.add(buttons.get(0));
        } else if (buttons.size() == 2) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
        } else if (buttons.size() == 3) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
        } else if (buttons.size() == 4) {
            row3.add(buttons.get(0));
            row3.add(buttons.get(1));
            row3.add(buttons.get(2));
            row3.add(buttons.get(3));
        }

        List<InlineKeyboardButton> row1 = new ArrayList<>();
        row1.add(prev);
        row1.add(delete);
        row1.add(next);

        INLINE_KEYBOARD_MARKUP.setKeyboard(Arrays.asList(row3, row2, row1));
        return INLINE_KEYBOARD_MARKUP;
    }

    private static List<InlineKeyboardButton> getCountButtons(int count) {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        switch (count) {
            case 1 -> {
                InlineKeyboardButton one = new InlineKeyboardButton("1");
                one.setCallbackData("pr1");
                buttons.add(one);
            }
            case 2 -> {
                InlineKeyboardButton two_one = new InlineKeyboardButton("1");
                two_one.setCallbackData("pr1");
                InlineKeyboardButton two = new InlineKeyboardButton("2");
                two.setCallbackData("pr2");
                buttons.add(two_one);
                buttons.add(two);
            }
            case 3 -> {
                InlineKeyboardButton three_one = new InlineKeyboardButton("1");
                three_one.setCallbackData("pr1");
                InlineKeyboardButton three_two = new InlineKeyboardButton("2");
                three_two.setCallbackData("pr2");
                InlineKeyboardButton three = new InlineKeyboardButton("3");
                three.setCallbackData("pr3");
                buttons.add(three_one);
                buttons.add(three_two);
                buttons.add(three);
            }
            case 4 -> {
                InlineKeyboardButton four_one = new InlineKeyboardButton("1");
                four_one.setCallbackData("pr1");
                InlineKeyboardButton four_two = new InlineKeyboardButton("2");
                four_two.setCallbackData("pr2");
                InlineKeyboardButton four_three = new InlineKeyboardButton("3");
                four_three.setCallbackData("pr3");
                InlineKeyboardButton four = new InlineKeyboardButton("4");
                four.setCallbackData("pr4");
                buttons.add(four_one);
                buttons.add(four_two);
                buttons.add(four_three);
                buttons.add(four);
            }
            case 5 -> {
                InlineKeyboardButton five_one = new InlineKeyboardButton("1");
                five_one.setCallbackData("pr1");
                InlineKeyboardButton five_two = new InlineKeyboardButton("2");
                five_two.setCallbackData("pr2");
                InlineKeyboardButton five_three = new InlineKeyboardButton("3");
                five_three.setCallbackData("pr3");
                InlineKeyboardButton five_four = new InlineKeyboardButton("4");
                five_four.setCallbackData("pr4");
                InlineKeyboardButton five = new InlineKeyboardButton("5");
                five.setCallbackData("pr5");
                buttons.add(five_one);
                buttons.add(five_two);
                buttons.add(five_three);
                buttons.add(five_four);
                buttons.add(five);
            }
            case 6 -> {
                InlineKeyboardButton six_one = new InlineKeyboardButton("1");
                six_one.setCallbackData("pr1");
                InlineKeyboardButton six_two = new InlineKeyboardButton("2");
                six_two.setCallbackData("pr2");
                InlineKeyboardButton six_three = new InlineKeyboardButton("3");
                six_three.setCallbackData("pr3");
                InlineKeyboardButton six_four = new InlineKeyboardButton("4");
                six_four.setCallbackData("pr4");
                InlineKeyboardButton six_five = new InlineKeyboardButton("5");
                six_five.setCallbackData("pr5");
                InlineKeyboardButton six = new InlineKeyboardButton("6");
                six.setCallbackData("pr6");
                buttons.add(six_one);
                buttons.add(six_two);
                buttons.add(six_three);
                buttons.add(six_four);
                buttons.add(six_five);
                buttons.add(six);
            }
            case 7 -> {
                InlineKeyboardButton seven_one = new InlineKeyboardButton("1");
                seven_one.setCallbackData("pr1");
                InlineKeyboardButton seven_two = new InlineKeyboardButton("2");
                seven_two.setCallbackData("pr2");
                InlineKeyboardButton seven_three = new InlineKeyboardButton("3");
                seven_three.setCallbackData("pr3");
                InlineKeyboardButton seven_four = new InlineKeyboardButton("4");
                seven_four.setCallbackData("pr4");
                InlineKeyboardButton seven_five = new InlineKeyboardButton("5");
                seven_five.setCallbackData("pr5");
                InlineKeyboardButton seven_six = new InlineKeyboardButton("6");
                seven_six.setCallbackData("pr6");
                InlineKeyboardButton seven = new InlineKeyboardButton("7");
                seven.setCallbackData("pr7");
                buttons.add(seven_one);
                buttons.add(seven_two);
                buttons.add(seven_three);
                buttons.add(seven_four);
                buttons.add(seven_five);
                buttons.add(seven_six);
                buttons.add(seven);
            }
            case 8 -> {
                InlineKeyboardButton eight_one = new InlineKeyboardButton("1");
                eight_one.setCallbackData("pr1");
                InlineKeyboardButton eight_two = new InlineKeyboardButton("2");
                eight_two.setCallbackData("pr2");
                InlineKeyboardButton eight_three = new InlineKeyboardButton("3");
                eight_three.setCallbackData("pr3");
                InlineKeyboardButton eight_four = new InlineKeyboardButton("4");
                eight_four.setCallbackData("pr4");
                InlineKeyboardButton eight_five = new InlineKeyboardButton("5");
                eight_five.setCallbackData("pr5");
                InlineKeyboardButton eight_six = new InlineKeyboardButton("6");
                eight_six.setCallbackData("pr6");
                InlineKeyboardButton eight_seven = new InlineKeyboardButton("7");
                eight_seven.setCallbackData("pr7");
                InlineKeyboardButton eight = new InlineKeyboardButton("8");
                eight.setCallbackData("pr8");
                buttons.add(eight_one);
                buttons.add(eight_two);
                buttons.add(eight_three);
                buttons.add(eight_four);
                buttons.add(eight_five);
                buttons.add(eight_six);
                buttons.add(eight_seven);
                buttons.add(eight);
            }
            case 9 -> {
                InlineKeyboardButton nine_one = new InlineKeyboardButton("1");
                nine_one.setCallbackData("pr1");
                InlineKeyboardButton nine_two = new InlineKeyboardButton("2");
                nine_two.setCallbackData("pr2");
                InlineKeyboardButton nine_three = new InlineKeyboardButton("3");
                nine_three.setCallbackData("pr3");
                InlineKeyboardButton nine_four = new InlineKeyboardButton("4");
                nine_four.setCallbackData("pr4");
                InlineKeyboardButton nine_five = new InlineKeyboardButton("5");
                nine_five.setCallbackData("pr5");
                InlineKeyboardButton nine_six = new InlineKeyboardButton("6");
                nine_six.setCallbackData("pr6");
                InlineKeyboardButton nine_seven = new InlineKeyboardButton("7");
                nine_seven.setCallbackData("pr7");
                InlineKeyboardButton nine_eight = new InlineKeyboardButton("8");
                nine_eight.setCallbackData("pr8");
                InlineKeyboardButton nine = new InlineKeyboardButton("9");
                nine.setCallbackData("pr9");
                buttons.add(nine_one);
                buttons.add(nine_two);
                buttons.add(nine_three);
                buttons.add(nine_four);
                buttons.add(nine_five);
                buttons.add(nine_six);
                buttons.add(nine_seven);
                buttons.add(nine_eight);
                buttons.add(nine);
            }
            default -> {
                InlineKeyboardButton default_one = new InlineKeyboardButton("1");
                default_one.setCallbackData("pr1");
                InlineKeyboardButton default_two = new InlineKeyboardButton("2");
                default_two.setCallbackData("pr2");
                InlineKeyboardButton default_three = new InlineKeyboardButton("3");
                default_three.setCallbackData("pr3");
                InlineKeyboardButton default_four = new InlineKeyboardButton("4");
                default_four.setCallbackData("pr4");
                InlineKeyboardButton default_five = new InlineKeyboardButton("5");
                default_five.setCallbackData("pr5");
                InlineKeyboardButton default_six = new InlineKeyboardButton("6");
                default_six.setCallbackData("pr6");
                InlineKeyboardButton default_seven = new InlineKeyboardButton("7");
                default_seven.setCallbackData("pr7");
                InlineKeyboardButton default_eight = new InlineKeyboardButton("8");
                default_eight.setCallbackData("pr8");
                InlineKeyboardButton default_nine = new InlineKeyboardButton("9");
                default_nine.setCallbackData("pr9");
                InlineKeyboardButton default_teen = new InlineKeyboardButton("10");
                default_teen.setCallbackData("pr10");
                buttons.add(default_one);
                buttons.add(default_two);
                buttons.add(default_three);
                buttons.add(default_four);
                buttons.add(default_five);
                buttons.add(default_six);
                buttons.add(default_seven);
                buttons.add(default_eight);
                buttons.add(default_nine);
                buttons.add(default_teen);
            }
        }
        return buttons;
    }

    private static List<InlineKeyboardButton> setCountButtons(int count, String offset) {
        List<InlineKeyboardButton> buttons = new ArrayList<>();
        switch (count) {
            case 1 -> {
                InlineKeyboardButton one = new InlineKeyboardButton(offset.charAt(0) + "1");
                one.setCallbackData("pr" + offset.charAt(0) + "1");
                buttons.add(one);
            }
            case 2 -> {
                InlineKeyboardButton two_one = new InlineKeyboardButton(offset.charAt(0) + "1");
                two_one.setCallbackData("pr" + offset.charAt(0) + "1");
                InlineKeyboardButton two = new InlineKeyboardButton(offset.charAt(0) + "2");
                two.setCallbackData("pr" + offset.charAt(0) + "2");
                buttons.add(two_one);
                buttons.add(two);
            }
            case 3 -> {
                InlineKeyboardButton three_one = new InlineKeyboardButton(offset.charAt(0) + "1");
                three_one.setCallbackData("pr" + offset.charAt(0) + "1");
                InlineKeyboardButton three_two = new InlineKeyboardButton(offset.charAt(0) + "2");
                three_two.setCallbackData("pr" + offset.charAt(0) + "2");
                InlineKeyboardButton three = new InlineKeyboardButton(offset.charAt(0) + "3");
                three.setCallbackData("pr" + offset.charAt(0) + "3");
                buttons.add(three_one);
                buttons.add(three_two);
                buttons.add(three);
            }
            case 4 -> {
                InlineKeyboardButton four_one = new InlineKeyboardButton(offset.charAt(0) + "1");
                four_one.setCallbackData("pr" + offset.charAt(0) + "1");
                InlineKeyboardButton four_two = new InlineKeyboardButton(offset.charAt(0) + "2");
                four_two.setCallbackData("pr" + offset.charAt(0) + "2");
                InlineKeyboardButton four_three = new InlineKeyboardButton(offset.charAt(0) + "3");
                four_three.setCallbackData("pr" + offset.charAt(0) + "3");
                InlineKeyboardButton four = new InlineKeyboardButton(offset.charAt(0) + "4");
                four.setCallbackData("pr" + offset.charAt(0) + "4");
                buttons.add(four_one);
                buttons.add(four_two);
                buttons.add(four_three);
                buttons.add(four);
            }
            case 5 -> {
                InlineKeyboardButton five_one = new InlineKeyboardButton(offset.charAt(0) + "1");
                five_one.setCallbackData("pr" + offset.charAt(0) + "1");
                InlineKeyboardButton five_two = new InlineKeyboardButton(offset.charAt(0) + "2");
                five_two.setCallbackData("pr" + offset.charAt(0) + "2");
                InlineKeyboardButton five_three = new InlineKeyboardButton(offset.charAt(0) + "3");
                five_three.setCallbackData("pr" + offset.charAt(0) + "3");
                InlineKeyboardButton five_four = new InlineKeyboardButton(offset.charAt(0) + "4");
                five_four.setCallbackData("pr" + offset.charAt(0) + "4");
                InlineKeyboardButton five = new InlineKeyboardButton(offset.charAt(0) + "5");
                five.setCallbackData("pr" + offset.charAt(0) + "5");
                buttons.add(five_one);
                buttons.add(five_two);
                buttons.add(five_three);
                buttons.add(five_four);
                buttons.add(five);
            }
            case 6 -> {
                InlineKeyboardButton six_one = new InlineKeyboardButton(offset.charAt(0) + "1");
                six_one.setCallbackData("pr" + offset.charAt(0) + "1");
                InlineKeyboardButton six_two = new InlineKeyboardButton(offset.charAt(0) + "2");
                six_two.setCallbackData("pr" + offset.charAt(0) + "2");
                InlineKeyboardButton six_three = new InlineKeyboardButton(offset.charAt(0) + "3");
                six_three.setCallbackData("pr" + offset.charAt(0) + "3");
                InlineKeyboardButton six_four = new InlineKeyboardButton(offset.charAt(0) + "4");
                six_four.setCallbackData("pr" + offset.charAt(0) + "4");
                InlineKeyboardButton six_five = new InlineKeyboardButton(offset.charAt(0) + "5");
                six_five.setCallbackData("pr" + offset.charAt(0) + "5");
                InlineKeyboardButton six = new InlineKeyboardButton(offset.charAt(0) + "6");
                six.setCallbackData("pr" + offset.charAt(0) + "6");
                buttons.add(six_one);
                buttons.add(six_two);
                buttons.add(six_three);
                buttons.add(six_four);
                buttons.add(six_five);
                buttons.add(six);
            }
            case 7 -> {
                InlineKeyboardButton seven_one = new InlineKeyboardButton(offset.charAt(0) + "1");
                seven_one.setCallbackData("pr" + offset.charAt(0) + "1");
                InlineKeyboardButton seven_two = new InlineKeyboardButton(offset.charAt(0) + "2");
                seven_two.setCallbackData("pr" + offset.charAt(0) + "2");
                InlineKeyboardButton seven_three = new InlineKeyboardButton(offset.charAt(0) + "3");
                seven_three.setCallbackData("pr" + offset.charAt(0) + "3");
                InlineKeyboardButton seven_four = new InlineKeyboardButton(offset.charAt(0) + "4");
                seven_four.setCallbackData("pr" + offset.charAt(0) + "4");
                InlineKeyboardButton seven_five = new InlineKeyboardButton(offset.charAt(0) + "5");
                seven_five.setCallbackData("pr" + offset.charAt(0) + "5");
                InlineKeyboardButton seven_six = new InlineKeyboardButton(offset.charAt(0) + "6");
                seven_six.setCallbackData("pr" + offset.charAt(0) + "6");
                InlineKeyboardButton seven = new InlineKeyboardButton(offset.charAt(0) + "7");
                seven.setCallbackData("pr" + offset.charAt(0) + "7");
                buttons.add(seven_one);
                buttons.add(seven_two);
                buttons.add(seven_three);
                buttons.add(seven_four);
                buttons.add(seven_five);
                buttons.add(seven_six);
                buttons.add(seven);
            }
            case 8 -> {
                InlineKeyboardButton eight_one = new InlineKeyboardButton(offset.charAt(0) + "1");
                eight_one.setCallbackData("pr" + offset.charAt(0) + "1");
                InlineKeyboardButton eight_two = new InlineKeyboardButton(offset.charAt(0) + "2");
                eight_two.setCallbackData("pr" + offset.charAt(0) + "2");
                InlineKeyboardButton eight_three = new InlineKeyboardButton(offset.charAt(0) + "3");
                eight_three.setCallbackData("pr" + offset.charAt(0) + "3");
                InlineKeyboardButton eight_four = new InlineKeyboardButton(offset.charAt(0) + "4");
                eight_four.setCallbackData("pr" + offset.charAt(0) + "4");
                InlineKeyboardButton eight_five = new InlineKeyboardButton(offset.charAt(0) + "5");
                eight_five.setCallbackData("pr" + offset.charAt(0) + "5");
                InlineKeyboardButton eight_six = new InlineKeyboardButton(offset.charAt(0) + "6");
                eight_six.setCallbackData("pr" + offset.charAt(0) + "6");
                InlineKeyboardButton eight_seven = new InlineKeyboardButton(offset.charAt(0) + "7");
                eight_seven.setCallbackData("pr" + offset.charAt(0) + "7");
                InlineKeyboardButton eight = new InlineKeyboardButton(offset.charAt(0) + "8");
                eight.setCallbackData("pr" + offset.charAt(0) + "8");
                buttons.add(eight_one);
                buttons.add(eight_two);
                buttons.add(eight_three);
                buttons.add(eight_four);
                buttons.add(eight_five);
                buttons.add(eight_six);
                buttons.add(eight_seven);
                buttons.add(eight);
            }
            case 9 -> {
                InlineKeyboardButton nine_one = new InlineKeyboardButton(offset.charAt(0) + "1");
                nine_one.setCallbackData("pr" + offset.charAt(0) + "1");
                InlineKeyboardButton nine_two = new InlineKeyboardButton(offset.charAt(0) + "2");
                nine_two.setCallbackData("pr" + offset.charAt(0) + "2");
                InlineKeyboardButton nine_three = new InlineKeyboardButton(offset.charAt(0) + "3");
                nine_three.setCallbackData("pr" + offset.charAt(0) + "3");
                InlineKeyboardButton nine_four = new InlineKeyboardButton(offset.charAt(0) + "4");
                nine_four.setCallbackData("pr" + offset.charAt(0) + "4");
                InlineKeyboardButton nine_five = new InlineKeyboardButton(offset.charAt(0) + "5");
                nine_five.setCallbackData("pr" + offset.charAt(0) + "5");
                InlineKeyboardButton nine_six = new InlineKeyboardButton(offset.charAt(0) + "6");
                nine_six.setCallbackData("pr" + offset.charAt(0) + "6");
                InlineKeyboardButton nine_seven = new InlineKeyboardButton(offset.charAt(0) + "7");
                nine_seven.setCallbackData("pr" + offset.charAt(0) + "7");
                InlineKeyboardButton nine_eight = new InlineKeyboardButton(offset.charAt(0) + "8");
                nine_eight.setCallbackData("pr" + offset.charAt(0) + "8");
                InlineKeyboardButton nine = new InlineKeyboardButton(offset.charAt(0) + "9");
                nine.setCallbackData("pr" + offset.charAt(0) + "9");
                buttons.add(nine_one);
                buttons.add(nine_two);
                buttons.add(nine_three);
                buttons.add(nine_four);
                buttons.add(nine_five);
                buttons.add(nine_six);
                buttons.add(nine_seven);
                buttons.add(nine_eight);
                buttons.add(nine);
            }
            default -> {
                InlineKeyboardButton default_one = new InlineKeyboardButton(offset.charAt(0) + "1");
                default_one.setCallbackData("pr" + offset.charAt(0) + "1");
                InlineKeyboardButton default_two = new InlineKeyboardButton(offset.charAt(0) + "2");
                default_two.setCallbackData("pr" + offset.charAt(0) + "2");
                InlineKeyboardButton default_three = new InlineKeyboardButton(offset.charAt(0) + "3");
                default_three.setCallbackData("pr" + offset.charAt(0) + "3");
                InlineKeyboardButton default_four = new InlineKeyboardButton(offset.charAt(0) + "4");
                default_four.setCallbackData("pr" + offset.charAt(0) + "4");
                InlineKeyboardButton default_five = new InlineKeyboardButton(offset.charAt(0) + "5");
                default_five.setCallbackData("pr" + offset.charAt(0) + "5");
                InlineKeyboardButton default_six = new InlineKeyboardButton(offset.charAt(0) + "6");
                default_six.setCallbackData("pr" + offset.charAt(0) + "6");
                InlineKeyboardButton default_seven = new InlineKeyboardButton(offset.charAt(0) + "7");
                default_seven.setCallbackData("pr" + offset.charAt(0) + "7");
                InlineKeyboardButton default_eight = new InlineKeyboardButton(offset.charAt(0) + "8");
                default_eight.setCallbackData("pr" + offset.charAt(0) + "8");
                InlineKeyboardButton default_nine = new InlineKeyboardButton(offset.charAt(0) + "9");
                default_nine.setCallbackData("pr" + offset.charAt(0) + "9");
                InlineKeyboardButton default_teen = new InlineKeyboardButton((Integer.parseInt(offset.substring(0, 1)) + 1) + "0");
                default_teen.setCallbackData("pr" + (Integer.parseInt(offset.substring(0, 1)) + 1) + "0");
                buttons.add(default_one);
                buttons.add(default_two);
                buttons.add(default_three);
                buttons.add(default_four);
                buttons.add(default_five);
                buttons.add(default_six);
                buttons.add(default_seven);
                buttons.add(default_eight);
                buttons.add(default_nine);
                buttons.add(default_teen);
            }
        }
        return buttons;
    }
}
