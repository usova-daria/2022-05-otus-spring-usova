package com.otus.spring.ui.impl;

import com.otus.spring.model.Answer;
import com.otus.spring.model.Question;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.otus.spring.ui.impl.Printer.NEW_LINE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class QuestionsFormatterTest {

    private final QuestionsFormatter questionsFormatter = new QuestionsFormatter();

    @Test
    void happyCase_Test() {
        Map<Answer, Boolean> answers = new LinkedHashMap<>();
        answers.put(new Answer("First answer"), true);
        answers.put(new Answer("Second answer"), false);
        Question question = new Question("Question?", answers);

        String expected = "Question?" + NEW_LINE +
                "1. First answer" + NEW_LINE +
                "2. Second answer" + NEW_LINE;

        assertEquals(expected, questionsFormatter.format(question));
    }

}