package com.otus.spring.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class QuestionTest {

    @Test
    void isCorrect_Test() {
        Answer correctAnswer = new Answer("Correct answer");
        Answer incorrectAnswer = new Answer("Incorrect answer");

        Question question = new Question("Question?", Map.ofEntries(
                Map.entry(correctAnswer, true),
                Map.entry(incorrectAnswer, false)
        ));

        assertTrue( question.isCorrect( List.of(correctAnswer) ) );
        assertFalse( question.isCorrect( List.of(incorrectAnswer) ) );
        assertFalse( question.isCorrect( List.of(correctAnswer, incorrectAnswer) ) );
    }

    @Test
    void getAnswers_Test() {
        Answer correctAnswer = new Answer("Correct answer");
        Answer incorrectAnswer = new Answer("Incorrect answer");

        Question question = new Question("Question?", Map.ofEntries(
                Map.entry(correctAnswer, true),
                Map.entry(incorrectAnswer, false)
        ));

        List<Answer> answers = question.getAnswers();
        assertEquals(2, answers.size());
        assertTrue( answers.contains(correctAnswer) );
        assertTrue( answers.contains(incorrectAnswer) );
    }

}