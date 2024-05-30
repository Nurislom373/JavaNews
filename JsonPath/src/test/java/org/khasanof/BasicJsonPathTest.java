package org.khasanof;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 4/6/2024 12:24 AM
 */
public class BasicJsonPathTest {

    @Test
    void firstTestDotNotationExpressionShouldSuccess() throws IOException {
        String fileAsString = GuavaResourceReader.getResourceFileAsString("data/book.json");
        String title = JsonPath.read(fileAsString, "$.store.book[0].title");

        assertNotNull(title);
        assertEquals("Sayings of the Century", title);
    }

    @Test
    void firstTestBracketNotationExpressionShouldSuccess() throws IOException {
        String fileAsString = GuavaResourceReader.getResourceFileAsString("data/book.json");
        String title = JsonPath.read(fileAsString, "$['store']['book'][0]['title']");

        assertNotNull(title);
        assertEquals("Sayings of the Century", title);
    }

    @Test
    void secondTestDotNotationExpressionShouldSuccess() throws IOException {
        String fileAsString = GuavaResourceReader.getResourceFileAsString("data/book.json");
        List<String> authors = JsonPath.read(fileAsString, "$.store.book[*].author");

        assertNotNull(authors);
        assertFalse(authors.isEmpty());
        assertEquals(authors.size(), 4);
        assertEquals(authors.get(0), "Nigel Rees");
        assertEquals(authors.get(1), "Evelyn Waugh");
        assertEquals(authors.get(2), "Herman Melville");
        assertEquals(authors.get(3), "J. R. R. Tolkien");
    }

    @Test
    void thirdTestDotNotationExpressionShouldSuccess() throws IOException {
        String fileAsString = GuavaResourceReader.getResourceFileAsString("data/book.json");
        List<String> authors = JsonPath.read(fileAsString, "$..author");

        assertNotNull(authors);
        assertFalse(authors.isEmpty());
        assertEquals(authors.size(), 4);
        assertEquals(authors.get(0), "Nigel Rees");
        assertEquals(authors.get(1), "Evelyn Waugh");
        assertEquals(authors.get(2), "Herman Melville");
        assertEquals(authors.get(3), "J. R. R. Tolkien");
    }

    @Test
    void fourthTestDotNotationExpressionShouldSuccess() throws IOException {
        String fileAsString = GuavaResourceReader.getResourceFileAsString("data/book.json");
        JSONArray booksAndBicycles = JsonPath.read(fileAsString, "$.store.*");

        assertNotNull(booksAndBicycles);
    }

    @Test
    void fifthTestDotNotationExpressionShouldSuccess() throws IOException {
        String fileAsString = GuavaResourceReader.getResourceFileAsString("data/book.json");
        Integer length = JsonPath.read(fileAsString, "$..book.length()");

        assertNotNull(length);
        assertEquals(length, 4);
    }
}
