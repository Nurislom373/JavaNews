package bot.service.product;

import bot.enums.Language;
import bot.handlers.message.MessageHandler;
import bot.models.product.Product;
import bot.repository.product.ProductRp;
import bot.utils.BaseUtils;

import java.util.List;

public class ProductService {
    public static String All() {
        String result = "";
        List<Product> products = ProductRp.instance.getAll();
        if (products.size() > 10) {
            for (int i = 0; i < 10; i++) {
                result += "<b>" + products.get(i).getId() + "</b>. " + BaseUtils.translate(MessageHandler.user.getLanguage(), Language.NAME) + ": <b>" + products.get(i).getName() + "</b> - " + BaseUtils.translate(MessageHandler.user.getLanguage(), Language.PRICE) + ": <b>" + products.get(i).getPrice() + "</b>" + "\n";
            }
        } else {
            for (Product product : products) {
                result += "<b>" + product.getId() + "</b>. " + BaseUtils.translate(MessageHandler.user.getLanguage(), Language.NAME) + ": <b>" + product.getName() + "</b> - " + BaseUtils.translate(MessageHandler.user.getLanguage(), Language.PRICE) + ": <b>" + product.getPrice() + "</b>" + "\n";
            }
        }
        return result;
    }

    public static String offsetAll(int offset) {
        String result = "";
        List<Product> products = ProductRp.instance.getAllOffset(offset);
        for (Product product : products) {
            result += "<b>" + product.getId() + "</b>. " + BaseUtils.translate(MessageHandler.user.getLanguage(), Language.NAME) + ": <b>" + product.getName() + "</b> - " + BaseUtils.translate(MessageHandler.user.getLanguage(), Language.PRICE) + ": <b>" + product.getPrice() + "</b>" + "\n";
        }
        return result;
    }

    public static void main(String[] args) {
        All();
    }
}
