package bot.service.product;

import bot.enums.Language;
import bot.handlers.message.MessageHandler;
import bot.models.product.BuyProduct;
import bot.models.product.Product;
import bot.utils.BaseUtils;

import java.util.ArrayList;
import java.util.List;

public class BasketService {
    private static Product chance_product = new Product();
    public static List<BuyProduct> Basket = new ArrayList<>();

    public static void add(BuyProduct product) {
        if (isPresent(product)) {
            editProduct(product);
        } else {
            Basket.add(product);
        }
    }

    public static BuyProduct getById(int id) {
        return Basket.get(id);
    }

    public static void editProduct(BuyProduct product) {
        for (BuyProduct buyProduct : Basket) {
            if (product.getName().equals(buyProduct.getName())) {
                buyProduct.setCount(product.getCount() + buyProduct.getCount());
            }
        }
    }

    public static boolean isPresent(BuyProduct product) {
        for (BuyProduct buyProduct : Basket) {
            if (product.getName().equals(buyProduct.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void setChance(Product product) {
        chance_product = product;
    }

    public static Product getChance() {
        return chance_product;
    }

    public static String show() {
        String result = "";
        int counter = 1;
        for (BuyProduct product : Basket) {
            result += "<b>" + counter + "</b>. " + BaseUtils.translate(MessageHandler.user.getLanguage(), Language.NAME) + ": <b>" + product.getName() + "</b> - " + BaseUtils.translate(MessageHandler.user.getLanguage(), Language.PRICE) + ": <b>" + (product.getPrice() * product.getCount()) + "</b> - " + BaseUtils.translate(MessageHandler.user.getLanguage(), Language.COUNT) + ": <b>" + product.getCount() + "</b>" + "\n";
            counter++;
        }
        return result;
    }
}
