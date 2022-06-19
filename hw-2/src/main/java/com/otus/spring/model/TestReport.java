package com.otus.spring.model;

import java.util.List;

public class TestReport {

    private final Credentials credentials;
    private final TestResults results;

    public TestReport(Credentials credentials) {
        this.credentials = credentials;
        this.results = new TestResults();
    }

    public void addResult(Question question, List<Answer> answers) {
        results.totalNumberOfQuestions++;
        if (question.isCorrect(answers)) {
            results.numberOfCorrectAnswers++;
        }
    }

    public TestResults getResults() {
        return results;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public static class Credentials {
        private final String firstName;
        private final String lastName;

        public Credentials(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public String getLastName() {
            return lastName;
        }

    }

    public static class TestResults {
        private int totalNumberOfQuestions = 0;
        private int numberOfCorrectAnswers = 0;

        public int getTotalNumberOfQuestions() {
            return totalNumberOfQuestions;
        }

        public int getNumberOfCorrectAnswers() {
            return numberOfCorrectAnswers;
        }
    }

}
