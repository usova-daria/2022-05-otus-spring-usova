package com.otus.spring.model;

import java.util.Objects;

public class Answer {

    private final String text;

    public Answer(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return Objects.equals(text, answer.text);
    }

    @Override
    public int hashCode() {
        return Objects.hash(text);
    }

}
