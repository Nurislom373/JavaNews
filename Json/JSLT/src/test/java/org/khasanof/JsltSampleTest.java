package org.khasanof;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.schibsted.spt.data.jslt.Expression;
import com.schibsted.spt.data.jslt.Parser;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 7/29/2024 10:58 AM
 */
public class JsltSampleTest {

    /**
     *
     */
    @Test
    void firstTestSimpleTransformShouldSuccess() {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream resourceAsStream = resourceLoader("/data1.jslt")) {
            String jslt = new String(resourceAsStream.readAllBytes());
            Expression exp = Parser.compileString(jslt);

            JsonNode input = mapper.readTree(resourceLoader("/data1.json"));
            JsonNode output = exp.apply(input);

            assertNotNull(output);
            System.out.println(output.toPrettyString());
            // mapper.writerWithDefaultPrettyPrinter().writeValue(new File("transformed_" + jsonFile), output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *
     */
    @Test
    void secondTestSimpleTransformShouldSuccess() {
        ObjectMapper mapper = new ObjectMapper();

        try (InputStream resourceAsStream = resourceLoader("/data2.jslt")) {
            String jslt = new String(resourceAsStream.readAllBytes());
            Expression exp = Parser.compileString(jslt);

            JsonNode input = mapper.readTree(resourceLoader("/data2.json"));
            JsonNode output = exp.apply(input);

            assertNotNull(output);
            System.out.println(output.toPrettyString());
            // mapper.writerWithDefaultPrettyPrinter().writeValue(new File("transformed_" + jsonFile), output);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream resourceLoader(String filename) {
        return getClass().getResourceAsStream(filename);
    }
}
