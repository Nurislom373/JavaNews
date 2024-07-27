package org.khasanof;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.victools.jsonschema.generator.*;
import com.github.victools.jsonschema.module.jackson.JacksonModule;
import com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationModule;
import org.junit.jupiter.api.Test;

import static com.github.victools.jsonschema.module.jackson.JacksonOption.RESPECT_JSONPROPERTY_REQUIRED;
import static com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationOption.INCLUDE_PATTERN_EXPRESSIONS;
import static com.github.victools.jsonschema.module.jakarta.validation.JakartaValidationOption.NOT_NULLABLE_FIELD_IS_REQUIRED;

/**
 * @author Nurislom
 * @see org.khasanof
 * @since 7/27/2024 9:34 PM
 */
public class JsonSchemaTest {

    /**
     *
     */
    @Test
    void firstTestGenerateJsonSchemaShouldSuccess() {
        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON);
        SchemaGeneratorConfig config = configBuilder
                .with(Option.EXTRA_OPEN_API_FORMAT_VALUES)
                .without(Option.FLATTENED_ENUMS_FROM_TOSTRING)
                .build();

        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(Article.class);

        System.out.println(jsonSchema.toPrettyString());
    }

    /**
     *
     */
    @Test
    void secondTestGenerateJsonSchemaWithJakartaValidationsShouldSuccess() {
        JakartaValidationModule jakartaValidationModule = new JakartaValidationModule(NOT_NULLABLE_FIELD_IS_REQUIRED, INCLUDE_PATTERN_EXPRESSIONS);

        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON);
        SchemaGeneratorConfig config = configBuilder
                .with(jakartaValidationModule)
                .build();

        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(JakartaArticle.class);

        System.out.println(jsonSchema.toPrettyString());
    }

    /**
     *
     */
    @Test
    void thirdTestGenerateJsonSchemaWithJacksonShouldSuccess() {
        JacksonModule jacksonModule = new JacksonModule(RESPECT_JSONPROPERTY_REQUIRED);

        SchemaGeneratorConfigBuilder configBuilder = new SchemaGeneratorConfigBuilder(SchemaVersion.DRAFT_2020_12, OptionPreset.PLAIN_JSON);
        SchemaGeneratorConfig config = configBuilder
                .with(jacksonModule)
                .build();

        SchemaGenerator generator = new SchemaGenerator(config);
        JsonNode jsonSchema = generator.generateSchema(JacksonArticle.class);

        System.out.println(jsonSchema.toPrettyString());
    }
}
