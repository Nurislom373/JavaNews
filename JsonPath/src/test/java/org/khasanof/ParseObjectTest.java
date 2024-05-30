package org.khasanof;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.ReadContext;
import net.minidev.json.JSONObject;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 4/6/2024 2:33 AM
 */
public class ParseObjectTest {

    @Test
    void firstTestParseStringToObjectShouldSuccess() throws IOException {
        String fileAsString = GuavaResourceReader.getResourceFileAsString("data/book.json");
        Object parse = Configuration.defaultConfiguration()
                .jsonProvider()
                .parse(fileAsString);

        assertInstanceOf(LinkedHashMap.class, parse);
    }

    @Test
    void firstTestParsedObjectReadShouldSuccess() throws IOException {
        String fileAsString = GuavaResourceReader.getResourceFileAsString("data/book.json");
        Object document = Configuration.defaultConfiguration()
                .jsonProvider()
                .parse(fileAsString);

        String author0 = JsonPath.read(document, "$.store.book[0].author");
        String author1 = JsonPath.read(document, "$.store.book[1].author");

        assertEquals(author0, "Nigel Rees");
        assertEquals(author1, "Evelyn Waugh");
    }

    @Test
    void thirdTestReadContextShouldSuccess() throws IOException {
        String json = GuavaResourceReader.getResourceFileAsString("data/book.json");

        ReadContext ctx = JsonPath.parse(json);

        List<String> authorsOfBooksWithISBN = ctx.read("$.store.book[?(@.isbn)].author");
        assertEquals(authorsOfBooksWithISBN.size(), 2);

        List<Map<String, Object>> expensiveBooks = JsonPath
                .using(Configuration.defaultConfiguration())
                .parse(json)
                .read("$.store.book[?(@.price > 10)]", List.class);

        assertEquals(expensiveBooks.size(), 2);
    }
}
