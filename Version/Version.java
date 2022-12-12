import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Version {

    public static void main(String[] args) {
        try {
            List<String> allLines = Files.readAllLines(Path.of("src/main/resources/version.txt"));
            System.out.println("allLines = " + allLines);
            List<String> bubbleSort = bubbleSort(allLines);
            System.out.println("bubbleSort = " + bubbleSort);
            writeFile(bubbleSort);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static List<String> bubbleSort(List<String> lines) {
        for (int i = 0; i < lines.size() - 1; i++) {
            for (int j = 0; j < lines.size() - i - 1; j++) {
                if (checkStr(lines.get(j + 1), lines.get(j))) {
                    String line = lines.get(j);
                    lines.set(j, lines.get(j + 1));
                    lines.set(j + 1, line);
                }
            }
        }
        return lines;
    }

    public static boolean checkStr(String var1, String var2) {
        List<String> var1Tokens = getTokensWithCollection(var1, ".", false);
        List<String> var2Tokens = getTokensWithCollection(var2, ".", false);
        int min = Math.min(var1Tokens.size(), var2Tokens.size());

        boolean returnType = false;

        for (int i = 0; i < min; i++) {
            if (Float.parseFloat(var1Tokens.get(i)) == Float.parseFloat(var2Tokens.get(i)))
                continue;
            if (Float.parseFloat(var1Tokens.get(i)) > Float.parseFloat(var2Tokens.get(i))) {
                returnType = true;
            }
            break;
        }
        return returnType;
    }

    public static List<String> getTokensWithCollection(String var, String delim, boolean returnDelim) {
        return Collections.list(new StringTokenizer(var, delim, returnDelim))
                .stream()
                .map(token -> (String) token)
                .collect(Collectors.toList());
    }

    public static void writeFile(List<String> sortedLines) {
        delete("src/main/resources/sorted_version.txt");
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("src/main/resources/sorted_version.txt", true))) {
            sortedLines.forEach((line) -> {
                try {
                    bufferedWriter.append(line.concat("\n"));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void delete(String path) {
        if (new File(path).delete()) {
            System.out.println("File is successfully deleted.");
        } else {
            System.out.println("File doesn't exit");
        }
    }
}
