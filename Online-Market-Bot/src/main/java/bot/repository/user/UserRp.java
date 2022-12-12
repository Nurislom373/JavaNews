package bot.repository.user;

import bot.config.DBConnection;
import bot.config.PConfig;
import bot.models.User;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserRp {
    public static void add(User user) {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            String text = PConfig.get("query.auth.user.insert").formatted(user.getChatId(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getNumber(), user.getLanguage());
            statement.execute(text);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static User checkUserByChatId(Long chatId) {
        List<User> users = getAll();
        for (User user : users) {
            if (user.getChatId().equals(chatId)) {
                return user;
            }
        }
        return null;
    }

    public static List<User> getAll() {
        try {
            Statement statement = DBConnection.getConnection().createStatement();
            ResultSet query = statement.executeQuery(PConfig.get("query.user.get.all"));
            List<User> users = new ArrayList<>();
            while (query.next()) {
                User user = new User();
                user.setId(query.getLong("id"));
                user.setChatId(query.getLong("chat_id"));
                user.setUsername(query.getString("username"));
                user.setFirstName(query.getString("first_name"));
                user.setLastName(query.getString("last_name"));
                user.setNumber(query.getString("number"));
                user.setLanguage(query.getString("language"));
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>(0);
    }
}
