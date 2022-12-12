package bot.repository.BuyOrder;

import bot.config.DBConnection;
import bot.config.PConfig;
import bot.handlers.callBack.CallBackHandler;
import bot.handlers.message.MessageHandler;
import bot.models.User;
import bot.models.product.BuyProduct;
import bot.models.product.Product;
import bot.models.product.ShowProduct;
import bot.repository.product.ProductRp;
import bot.repository.user.UserRp;
import bot.service.product.BasketService;
import bot.utils.BaseUtils;
import org.sqlite.core.DB;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class BuyOrderRp {
    public static void add(BuyProduct product) {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String query = PConfig.get("query.buy_order_list.insert").formatted(MessageHandler.user.getId(), product.getId(), product.getCount(), (product.getPrice() * product.getCount()));
            statement.executeQuery(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<ShowProduct> getMyOrders() {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            User user = UserRp.checkUserByChatId(MessageHandler.user.getChatId());
            String query = PConfig.get("query.buy_order_list.select").formatted(user.getId());
            ResultSet res = statement.executeQuery(query);
            List<ShowProduct> showProducts = new ArrayList<>();
            while (res.next()) {
                ShowProduct showProduct = new ShowProduct();
                Product product = ProductRp.getById(res.getInt("product_id"));
                showProduct.setProductName(product.getName());
                showProduct.setCount(res.getInt("product_count"));
                showProduct.setTotalPrice(res.getLong("total_price"));
                showProduct.setBuyTime(res.getString("buy_time"));
                showProducts.add(showProduct);
            }
            return showProducts;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(0);
    }
}
