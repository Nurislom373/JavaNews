package bot.repository.product;

import bot.config.DBConnection;
import bot.config.PConfig;
import bot.models.product.Product;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductRp {
    public static final ProductRp instance = new ProductRp();

    public List<Product> getAll() {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet query = statement.executeQuery(PConfig.get("query.product.get.all"));
            List<Product> products = new ArrayList<>();
            while (query.next()) {
                Product product = new Product();
                product.setId(query.getLong("id"));
                product.setName(query.getString("name"));
                product.setDesc(query.getString("desc"));
                product.setImagePath(query.getString("imagePath"));
                product.setPrice(query.getLong("price"));
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(0);
    }

    public List<Product> getAllOffset(int offset) {
        try {
            String limit = "select * from product limit 10 offset " + offset;
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet query = statement.executeQuery(limit);
            List<Product> products = new ArrayList<>();
            while (query.next()) {
                Product product = new Product();
                product.setId(query.getLong("id"));
                product.setName(query.getString("name"));
                product.setDesc(query.getString("desc"));
                product.setImagePath(query.getString("imagePath"));
                product.setPrice(query.getLong("price"));
                products.add(product);
            }
            return products;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(0);
    }

    public static Product getById(int id) {
        Product product = new Product();
        try {
            String get = "select * from product where id = " + id + ";";
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet query = statement.executeQuery(get);
            product.setId(query.getLong("id"));
            product.setName(query.getString("name"));
            product.setDesc(query.getString("desc"));
            product.setImagePath(query.getString("imagePath"));
            product.setPrice(query.getLong("price"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return product;
    }


    public int getCount() {
        return getAll().size();
    }
}
