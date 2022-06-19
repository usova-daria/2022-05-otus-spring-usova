package com.otus.spring.dao.mapper;

import com.otus.spring.dao.validator.QuestionValidator;
import com.otus.spring.model.Answer;
import com.otus.spring.model.Question;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class QuestionMapper {

    public final static String CORRECT_ANSWER_SEPARATOR = "-";

    private final QuestionValidator validator;

    public QuestionMapper(QuestionValidator validator) {
        this.validator = validator;
    }

    public Question map(List<String> entries) {
        validator.validate(entries);

        return new Question(
                entries.get(0),
                mapAnswers(entries.subList(1, entries.size()))
        );
    }

    private Map<Answer, Boolean> mapAnswers(List<String> entries) {
        List<Answer> answers = entries.subList(0, entries.size() - 1)
                .stream()
                .map(Answer::new)
                .collect(Collectors.toList());

        Set<Integer> correctAnswers = getCorrectAnswerIndexes( entries.get(entries.size() - 1) );
        Map<Answer, Boolean> answersToCorrect = new LinkedHashMap<>();
        for (int i = 0; i < answers.size(); i++) {
            answersToCorrect.put(answers.get(i), correctAnswers.contains(i));
        }

        return answersToCorrect;
    }

    private Set<Integer> getCorrectAnswerIndexes(String correctAnswer) {
        return Arrays.stream(correctAnswer.split(CORRECT_ANSWER_SEPARATOR))
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

}
