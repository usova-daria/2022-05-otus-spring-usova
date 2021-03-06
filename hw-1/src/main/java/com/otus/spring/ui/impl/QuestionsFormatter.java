package com.otus.spring.ui.impl;

import com.otus.spring.model.Question;

public class QuestionsFormatter {

    public final static String NEW_LINE = System.lineSeparator();

    public String format(Question question) {
        StringBuilder formattedQuestion = new StringBuilder();
        formattedQuestion.append(question.getText());
        formattedQuestion.append(NEW_LINE);

        for (int i = 0; i < question.getAnswers().size(); i++) {
            formattedQuestion.append(i + 1)
                    .append(". ")
                    .append(question.getAnswers().get(i).getText())
                    .append(NEW_LINE);
        }

        return formattedQuestion.toString();
    }

}
