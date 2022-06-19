package com.otus.spring.dao.impl;

import com.otus.spring.exception.ValidationError;
import com.otus.spring.model.Question;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CsvRowParserTest {

    private final CsvRowParser parser = new CsvRowParser();

    @Test
    void happyCase_Test() {
        String input = "  \"Question?\" , \"this is, first, answer\" ,  ,second answer";
        List<String> output = parser.parse(input);

        assertEquals(3, output.size());
        assertEquals("Question?", output.get(0));
        assertEquals("this is, first, answer", output.get(1));
        assertEquals("second answer", output.get(2));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "\"Question?, first, second",
            "\"Question?, \"\" \"first\", second",
    })
    @NullSource
    void unhappyCase_Test(String input) {
        assertThrows(ValidationError.class, () -> parser.parse(input));
    }

}