package com.otus.spring.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Question {

    private final String text;

    /**
     * Key -- answer
     * Value -- is answer correct
     */
    private final Map<Answer, Boolean> answersMap;

    public Question(String text, Map<Answer, Boolean> answersMap) {
        this.text = text;
        this.answersMap = answersMap;
    }

    public String getText() {
        return text;
    }

    public List<Answer> getAnswers() {
        return Arrays.asList( answersMap.keySet().toArray(new Answer[0]) );
    }

    public boolean isCorrect(List<Answer> answers) {
        return answers.size() == answersMap.values().stream().filter(i -> i).count() &&
               answers.stream().allMatch(a -> answersMap.getOrDefault(a, false));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(text, question.text) && Objects.equals(answersMap, question.answersMap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text, answersMap);
    }

}
