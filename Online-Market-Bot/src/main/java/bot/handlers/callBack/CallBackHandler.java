package bot.handlers.callBack;

import bot.Market;
import bot.config.PConfig;
import bot.enums.Language;
import bot.handlers.IBaseHandler;
import bot.handlers.message.MessageHandler;
import bot.keyboards.InlineKeyboard;
import bot.keyboards.ReplyKeyboard;
import bot.models.User;
import bot.models.product.BuyProduct;
import bot.models.product.Product;
import bot.repository.BuyOrder.BuyOrderRp;
import bot.repository.product.ProductRp;
import bot.repository.user.UserRp;
import bot.service.product.BasketService;
import bot.service.product.ProductService;
import bot.utils.BaseUtils;
import bot.utils.Emojis;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageReplyMarkup;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.io.File;

import static bot.handlers.message.MessageHandler.user;

public class CallBackHandler implements IBaseHandler {
    public static final CallBackHandler instance = new CallBackHandler();
    private static final Market bot = Market.getInstance();
    public static int offset = 0;
    public static int count = 1;

    @Override
    public void process(Update update) {
        Long chatID = update.getCallbackQuery().getMessage().getChatId();
        if (update.hasCallbackQuery()) {
            switch (update.getCallbackQuery().getData()) {
                case "UZ", "EN", "RU" -> {
                    DeleteMessage deleteMessage = new DeleteMessage(chatID.toString(), update.getCallbackQuery().getMessage().getMessageId());
                    user.setLanguage(update.getCallbackQuery().getData());
                    SendMessage message = new SendMessage(chatID.toString(), "<b>" + BaseUtils.translate(user.getLanguage(), Language.WELCOME_TO) + "</b>");
                    SendMessage message1 = new SendMessage(chatID.toString(), "<b>" + BaseUtils.translate(user.getLanguage(), Language.CHOOSE_MENU) + "</b>");
                    message1.setReplyMarkup(ReplyKeyboard.enterMenu());
                    message1.setParseMode("html");
                    message.setParseMode("html");
                    UserRp.add(user);
                    bot.executeMessage(message);
                    bot.executeMessage(message1);
                    bot.executeMessage(deleteMessage);
                }
                case "ADD" -> {
                    DeleteMessage deleteMessage = new DeleteMessage(chatID.toString(), update.getCallbackQuery().getMessage().getMessageId());
                    String text = BaseUtils.translate(user.getLanguage(), Language.SUCCESS) + " " + BaseUtils.translate(user.getLanguage(), Language.ADD) + " " + Emojis.SUCCESS;
                    SendMessage message = new SendMessage(chatID.toString(), "<b>" + text + "</b>");
                    message.setParseMode("html");
                    bot.executeMessage(deleteMessage);
                    bot.executeMessage(message);
                    BuyProduct buyProduct = new BuyProduct();
                    buyProduct.setDesc(BasketService.getChance().getDesc());
                    buyProduct.setId(BasketService.getChance().getId());
                    buyProduct.setImagePath(BasketService.getChance().getImagePath());
                    buyProduct.setName(BasketService.getChance().getName());
                    buyProduct.setPrice(BasketService.getChance().getPrice());
                    buyProduct.setCount(count);
                    BasketService.add(buyProduct);
                    BasketService.show();
                    count = 1;
                }
                case "DELETE" -> {
                    DeleteMessage message = new DeleteMessage(chatID.toString(), update.getCallbackQuery().getMessage().getMessageId());
                    bot.executeMessage(message);
                }
                case "NEXT" -> {
                    offset += 10;
                    DeleteMessage deleteMessage = new DeleteMessage(chatID.toString(), update.getCallbackQuery().getMessage().getMessageId());
                    String text = "<b>" + BaseUtils.translate(user.getLanguage(), Language.RESULT) + " of " + ProductRp.instance.getCount() + "</b>" + "\n" + "\n"
                            + ProductService.offsetAll(offset);
                    SendMessage message = new SendMessage(chatID.toString(), text);
                    message.setParseMode("html");
                    message.setReplyMarkup(InlineKeyboard.nextProductButtons(offset));
                    bot.executeMessage(deleteMessage);
                    bot.executeMessage(message);
                }
                case "PREV" -> {
                    offset -= 10;
                    DeleteMessage deleteMessage = new DeleteMessage(chatID.toString(), update.getCallbackQuery().getMessage().getMessageId());
                    String text = "<b>" + BaseUtils.translate(user.getLanguage(), Language.RESULT) + " of " + ProductRp.instance.getCount() + "</b>" + "\n" + "\n"
                            + ProductService.offsetAll(offset);
                    SendMessage message = new SendMessage(chatID.toString(), text);
                    message.setParseMode("html");
                    if (offset == 0) {
                        message.setReplyMarkup(InlineKeyboard.productButtons());
                    } else {
                        message.setReplyMarkup(InlineKeyboard.nextProductButtons(offset));
                    }
                    bot.executeMessage(deleteMessage);
                    bot.executeMessage(message);
                }
                case "PLUS" -> {
                    count += 1;
                    EditMessageCaption messageCaption = new EditMessageCaption();
                    messageCaption.setChatId(chatID.toString());
                    messageCaption.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    messageCaption.setParseMode("html");
                    String text = BaseUtils.translate(user.getLanguage(), Language.NAME) + " : " + BasketService.getChance().getName() + "" + "\n"
                            + BaseUtils.translate(user.getLanguage(), Language.DESC) + " : " + BasketService.getChance().getDesc() + "\n"
                            + BaseUtils.translate(user.getLanguage(), Language.PRICE) + " : " + (BasketService.getChance().getPrice() * count) + "\n";
                    messageCaption.setCaption(text);
                    messageCaption.setReplyMarkup(InlineKeyboard.photo());
                    bot.executeMessage(messageCaption);
                }
                case "MINUS" -> {
                    if (count > 1) {
                        count -= 1;
                    }
                    EditMessageCaption messageCaption = new EditMessageCaption();
                    messageCaption.setChatId(chatID.toString());
                    messageCaption.setMessageId(update.getCallbackQuery().getMessage().getMessageId());
                    messageCaption.setParseMode("html");
                    String text = BaseUtils.translate(user.getLanguage(), Language.NAME) + " : " + BasketService.getChance().getName() + "" + "\n"
                            + BaseUtils.translate(user.getLanguage(), Language.DESC) + " : " + BasketService.getChance().getDesc() + "\n"
                            + BaseUtils.translate(user.getLanguage(), Language.PRICE) + " : " + (BasketService.getChance().getPrice() * count) + "\n";
                    messageCaption.setCaption(text);
                    messageCaption.setReplyMarkup(InlineKeyboard.photo());
                    bot.executeMessage(messageCaption);
                }
                case "BUY" -> {
                    DeleteMessage message = new DeleteMessage(chatID.toString(), update.getCallbackQuery().getMessage().getMessageId());
                    SendMessage message1 = new SendMessage(chatID.toString(), BaseUtils.translate(user.getLanguage(), Language.SUCCESSFULLY_BUY_PRODUCT) + " " + Emojis.SMILE);
                    if (BasketService.Basket.size() > 1) {
                        for (int i = 0; i < BasketService.Basket.size(); i++) {
                            BuyOrderRp.add(BasketService.getById(i));
                        }
                    } else {
                        BuyOrderRp.add(BasketService.getById(0));
                    }
                    bot.executeMessage(message);
                    bot.executeMessage(message1);
                }
            }
        }
        if (update.getCallbackQuery().getData().startsWith("pr")) {
            String data = update.getCallbackQuery().getData();
            int id = Integer.parseInt(data.substring(2));
            Product product = ProductRp.getById(id);
            BasketService.setChance(product);
            String text = BaseUtils.translate(user.getLanguage(), Language.NAME) + " : " + product.getName() + "" + "\n"
                    + BaseUtils.translate(user.getLanguage(), Language.DESC) + " : " + product.getDesc() + "\n"
                    + BaseUtils.translate(user.getLanguage(), Language.PRICE) + " : " + product.getPrice() + "\n";
            SendPhoto photo = new SendPhoto(chatID.toString(), new InputFile(new File(product.getImagePath())));
            photo.setCaption(text);
            photo.setReplyMarkup(InlineKeyboard.photo());
            bot.executePhoto(photo);
        }
    }

    public static CallBackHandler getInstance() {
        return instance;
    }
}
