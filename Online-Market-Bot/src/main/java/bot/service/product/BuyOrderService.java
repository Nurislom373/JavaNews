package bot.service.product;

import bot.models.product.ShowProduct;
import bot.repository.BuyOrder.BuyOrderRp;

import java.util.List;

public class BuyOrderService {
    public static String editUI() {
        List<ShowProduct> showProducts = BuyOrderRp.getMyOrders();
        String res = "";
        for (ShowProduct showProduct : showProducts) {
            res += "<b>Product Name</b> : " + showProduct.getProductName() + "\n"
            + "<b>Product Count</b> : " + showProduct.getCount() + "\n"
            + "<b>Product Total Price</b> : " + showProduct.getTotalPrice() + "\n"
            + "<b>Product Buy Time</b> : " + showProduct.getBuyTime() + "\n" + "\n";
        }
        return res;
    }
}
