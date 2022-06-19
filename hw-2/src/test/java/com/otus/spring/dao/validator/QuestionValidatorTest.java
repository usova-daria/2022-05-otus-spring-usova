package com.otus.spring.dao.validator;

import com.otus.spring.exception.ValidationError;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static com.otus.spring.dao.mapper.QuestionMapper.CORRECT_ANSWER_SEPARATOR;

class QuestionValidatorTest {

    private final static QuestionValidator validator = new QuestionValidator();

    @Test
    void happyCase_singleSelect_Test() {
        List<String> entries = List.of("Question?", "first answer", "second answer", "0");
        validator.validate(entries);
    }

    @Test
    void happyCase_multipleSelect_Test() {
        List<String> entries = List.of("Question?", "first answer", "second answer", joinCorrectAnswersWithSeparator("0", "1"));
        validator.validate(entries);
    }

    @ParameterizedTest
    @MethodSource("unhappyData")
    void unhappyCase_Test(List<String> entries) {
        assertThrows(ValidationError.class, () -> validator.validate(entries));
    }

    private static List<List<String>> unhappyData() {
        return List.of(
                List.of("Question?", "first answer", "0"),
                List.of("Question?", "first answer", "second answer"),
                List.of("Question?", "first answer", "second answer", "3"),
                List.of("Question?", "first answer", "second answer", joinCorrectAnswersWithSeparator("0", "12"))
        );
    }

    private static String joinCorrectAnswersWithSeparator(String... answers) {
        return String.join(CORRECT_ANSWER_SEPARATOR, answers);
    }

}