package com.otus.spring.model;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class TestReportTest {

    @Test
    void addResult_CorrectAnswer_Test() {
        TestReport report = new TestReport(new TestReport.Credentials("First name", "Last name"));

        Answer correctAnswer = new Answer("Correct answer");
        Answer incorrectAnswer = new Answer("Incorrect answer");

        Question question = new Question("Question?", Map.ofEntries(
                Map.entry(correctAnswer, true),
                Map.entry(incorrectAnswer, false)
        ));

        report.addResult(question, List.of(correctAnswer));
        assertEquals(1, report.getResults().getNumberOfCorrectAnswers());
        assertEquals(1, report.getResults().getTotalNumberOfQuestions());
    }

    @Test
    void addResult_IncorrectAnswer_Test() {
        TestReport report = new TestReport(new TestReport.Credentials("First name", "Last name"));

        Answer correctAnswer = new Answer("Correct answer");
        Answer incorrectAnswer = new Answer("Incorrect answer");

        Question question = new Question("Question?", Map.ofEntries(
                Map.entry(correctAnswer, true),
                Map.entry(incorrectAnswer, false)
        ));

        report.addResult(question, List.of(incorrectAnswer));
        assertEquals(0, report.getResults().getNumberOfCorrectAnswers());
        assertEquals(1, report.getResults().getTotalNumberOfQuestions());
    }

    @Test
    void addResult_SeriesOfQuestions_Test() {
        TestReport report = new TestReport(new TestReport.Credentials("First name", "Last name"));

        Answer correctAnswer = new Answer("Correct answer");
        Answer incorrectAnswer = new Answer("Incorrect answer");

        Question question = new Question("Question?", Map.ofEntries(
                Map.entry(correctAnswer, true),
                Map.entry(incorrectAnswer, false)
        ));

        report.addResult(question, List.of(incorrectAnswer));
        report.addResult(question, List.of(correctAnswer));
        assertEquals(1, report.getResults().getNumberOfCorrectAnswers());
        assertEquals(2, report.getResults().getTotalNumberOfQuestions());
    }

}