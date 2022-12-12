package bot.config;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Statement;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Migration {
    public static void init() {
        try (FileReader fileReader = new FileReader(PConfig.get("migration.path")); BufferedReader bufferedReader = new BufferedReader(fileReader)) {
            Statement s = DBConnection.getConnection().createStatement();
            String queryData = bufferedReader.lines().collect(Collectors.joining());
            StringTokenizer queries = new StringTokenizer(queryData, ";");
            while (queries.hasMoreTokens()) {
                s.addBatch(queries.nextToken());
            }
            s.executeBatch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
