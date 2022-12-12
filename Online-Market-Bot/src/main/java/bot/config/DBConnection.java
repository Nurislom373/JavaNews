package bot.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class DBConnection {
    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(PConfig.get("db.connection.path"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void close() {
        try {
            if (Objects.nonNull(connection) && !connection.isClosed()) {
                connection.commit();
                connection.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
