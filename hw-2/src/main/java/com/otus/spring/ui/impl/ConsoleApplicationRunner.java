package com.otus.spring.ui.impl;

import com.otus.spring.model.Answer;
import com.otus.spring.model.Question;
import com.otus.spring.model.TestReport;
import com.otus.spring.service.api.QuestionsService;
import com.otus.spring.ui.api.ApplicationRunner;
import com.otus.spring.ui.api.ResourceBundleHolder;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ConsoleApplicationRunner implements ApplicationRunner {

    private final QuestionsService questionsService;
    private final QuestionsFormatter questionsFormatter;
    private final Printer printer;
    private final Reader reader;
    private final ResourceBundleHolder resourceBundleHolder;
    private final AnswersParser answersParser;

    public ConsoleApplicationRunner(QuestionsService questionsService,
                                    QuestionsFormatter questionsFormatter,
                                    Printer printer, Reader reader,
                                    AnswersParser answersParser,
                                    ResourceBundleHolder resourceBundleHolder) {
        this.questionsService = questionsService;
        this.questionsFormatter = questionsFormatter;
        this.printer = printer;
        this.reader = reader;
        this.answersParser = answersParser;
        this.resourceBundleHolder = resourceBundleHolder;
    }

    @Override
    public void run() {
        try {
            welcome();
            TestReport.Credentials credentials = getCredentials();
            TestReport report = new TestReport(credentials);

            List<Question> questions = questionsService.getQuestions();
            questions.forEach(q -> askOneQuestion(q, report));
            printResults(report);
        } catch (RuntimeException e) {
            e.printStackTrace();
            printer.print("Unexpected exception. Exit program");
        }
    }

    private void welcome() {
        String pattern = resourceBundleHolder.getBundle().getString("welcome");
        printer.println( MessageFormat.format(pattern, AnswersParser.ANSWERS_SEPARATOR) );
    }

    private TestReport.Credentials getCredentials() {
        String firstName = readNotBlankInput(resourceBundleHolder.getBundle().getString("first.name"));
        String lastName = readNotBlankInput(resourceBundleHolder.getBundle().getString("last.name"));
        printer.println();
        return new TestReport.Credentials(firstName, lastName);
    }

    private void askOneQuestion(Question question, TestReport testReport) {
        printer.println( questionsFormatter.format(question) );
        List<Answer> answers = readAnswers(question);
        testReport.addResult(question, answers);
        printer.println();
    }

    private List<Answer> readAnswers(Question question) {
        try {
            String answer = readNotBlankInput(resourceBundleHolder.getBundle().getString("answer"));
            List<Answer> allAnswers = question.getAnswers();
            Set<Integer> userAnswers = answersParser.parseAnswers(answer, allAnswers.size());
            return userAnswers.stream()
                    .map(i -> allAnswers.get(i - 1))
                    .collect(Collectors.toList());
        } catch (InputFormatException e) {
            String pattern = resourceBundleHolder.getBundle().getString("wrong.format.exception");
            printer.println(MessageFormat.format(pattern, e.getCorrectFormat()));
            return readAnswers(question);
        }
    }

    private String readNotBlankInput(String inputMessage) {
        String input;
        do {
            printer.print(inputMessage + " ");
            input = reader.read();
        } while (input.isBlank());
        return input;
    }

    private void printResults(TestReport report) {
        String resultsPattern = resourceBundleHolder.getBundle().getString("results");
        String resultsMessage = MessageFormat.format(resultsPattern,
                report.getCredentials().getFirstName() + " " + report.getCredentials().getLastName(),
                report.getResults().getNumberOfCorrectAnswers(),
                report.getResults().getTotalNumberOfQuestions());

        printer.println(resultsMessage);
    }

}
