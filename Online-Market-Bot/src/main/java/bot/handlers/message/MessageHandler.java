package bot.handlers.message;

import bot.Market;
import bot.enums.Language;
import bot.handlers.IBaseHandler;
import bot.handlers.callBack.CallBackHandler;
import bot.keyboards.InlineKeyboard;
import bot.keyboards.ReplyKeyboard;
import bot.models.User;
import bot.repository.product.ProductRp;
import bot.repository.user.UserRp;
import bot.service.product.BasketService;
import bot.service.product.BuyOrderService;
import bot.service.product.ProductService;
import bot.utils.BaseUtils;
import bot.utils.Emojis;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.Objects;

public class MessageHandler implements IBaseHandler {
    public static final MessageHandler instance = new MessageHandler();
    private static final Market bot = Market.getInstance();
    public static User user = new User();

    @Override
    public void process(Update update) {
        Long chatID = update.getMessage().getChatId();
        if (update.getMessage().hasText()) {
            if (update.getMessage().getText().equals("/start")) {
                User check_user = UserRp.checkUserByChatId(chatID);
                if (Objects.nonNull(check_user)) {
                    user = check_user;
                    SendMessage message = new SendMessage(chatID.toString(), "<b>" + BaseUtils.translate(user.getLanguage(), Language.WELCOME_TO) + "</b>");
                    SendMessage message1 = new SendMessage(chatID.toString(), "<b>" + BaseUtils.translate(user.getLanguage(), Language.CHOOSE_MENU) + "</b>");
                    message1.setReplyMarkup(ReplyKeyboard.enterMenu());
                    message1.setParseMode("html");
                    message.setParseMode("html");
                    bot.executeMessage(message);
                    bot.executeMessage(message1);
                } else {
                    BaseUtils.initUser(update, user);
                    String text = "Choose bot language:";
                    SendMessage sendMessage = new SendMessage(chatID.toString(), "<b>" + text + "</b>");
                    sendMessage.setParseMode("html");
                    sendMessage.setReplyMarkup(InlineKeyboard.language());
                    bot.executeMessage(sendMessage);
                }
            } else if (update.getMessage().getText().equals(BaseUtils.translate(user.getLanguage(), Language.MENU) + " " + Emojis.MENU)) {
                SendMessage message = new SendMessage(chatID.toString(), BaseUtils.translate(user.getLanguage(), Language.CHOOSE_MENU) + " " + Emojis.BOTTOM);
                message.setReplyMarkup(ReplyKeyboard.menuMenu());
                bot.executeMessage(message);
            } else if (update.getMessage().getText().equals(BaseUtils.translate(user.getLanguage(), Language.SETTINGS) + " " + Emojis.SETTINGS)) {
                SendMessage message = new SendMessage(chatID.toString(), BaseUtils.translate(user.getLanguage(), Language.CHOOSE_MENU) + " " + Emojis.BOTTOM);
                message.setReplyMarkup(ReplyKeyboard.settings());
                bot.executeMessage(message);
            } else if (update.getMessage().getText().equals(BaseUtils.translate(user.getLanguage(), Language.BACK) + " " + Emojis.BACK)) {
                SendMessage message = new SendMessage(chatID.toString(), BaseUtils.translate(user.getLanguage(), Language.BACK_MENU) + " " + Emojis.BOTTOM);
                message.setReplyMarkup(ReplyKeyboard.enterMenu());
                bot.executeMessage(message);
            } else if (update.getMessage().getText().equals(BaseUtils.translate(user.getLanguage(), Language.TO_ORDER) + " " + Emojis.TO_ORDER)) {
                String text = "<b>" + BaseUtils.translate(user.getLanguage(), Language.RESULT) + " of " + ProductRp.instance.getCount() + "</b>" + "\n" + "\n"
                        + ProductService.All();
                SendMessage message = new SendMessage(chatID.toString(), text);
                message.setParseMode("html");
                message.setReplyMarkup(InlineKeyboard.productButtons());
                bot.executeMessage(message);
            } else if (update.getMessage().getText().equals(BaseUtils.translate(user.getLanguage(), Language.BASKET) + " " + Emojis.BASKET)) {
                String text = BasketService.show();
                SendMessage message = new SendMessage(chatID.toString(), text);
                message.setParseMode("html");
                message.setReplyMarkup(InlineKeyboard.basketButtons());
                bot.executeMessage(message);
            } else if (update.getMessage().getText().equals(BaseUtils.translate(user.getLanguage(), Language.MY_ORDER) + " " + Emojis.MY_ORDER)) {
                String text = BuyOrderService.editUI();
                SendMessage message = new SendMessage(chatID.toString(), text);
                message.setParseMode("html");
                bot.executeMessage(message);
            }
        }
    }

    public static MessageHandler getInstance() {
        return instance;
    }
}
