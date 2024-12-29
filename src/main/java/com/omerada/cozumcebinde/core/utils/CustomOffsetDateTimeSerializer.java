package com.omerada.cozumcebinde.core.utils;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class CustomOffsetDateTimeSerializer extends JsonSerializer<OffsetDateTime> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER
            = DateTimeFormatter.ofPattern(DateUtils.getPatternWithTime2());

    @Override
    public void serialize(OffsetDateTime value, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
            throws IOException {
        if (value == null) {
            throw new IOException("OffsetDateTime argument is null.");
        }
        jsonGenerator.writeString(DATE_TIME_FORMATTER.format(value));
    }
}