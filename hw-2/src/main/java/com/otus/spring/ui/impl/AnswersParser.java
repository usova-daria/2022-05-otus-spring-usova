package com.otus.spring.ui.impl;

import com.otus.spring.ui.api.ResourceBundleHolder;

import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class AnswersParser {

    public static String ANSWERS_SEPARATOR = ",";
    private final static String ANSWER_REGEX = "(\\d+\\s*" + ANSWERS_SEPARATOR + "*\\s*)+";
    private final static Pattern ANSWER_PATTERN = Pattern.compile(ANSWER_REGEX);

    private final ResourceBundleHolder resourceBundleHolder;

    public AnswersParser(ResourceBundleHolder resourceBundleHolder) {
        this.resourceBundleHolder = resourceBundleHolder;
    }

    public Set<Integer> parseAnswers(String input, int maxIndex) {
        if (input == null || !ANSWER_PATTERN.matcher(input).matches()) {
            String pattern = resourceBundleHolder.getBundle().getString("format.only.digits.with.separator");
            throw new InputFormatException( MessageFormat.format(pattern, ANSWERS_SEPARATOR) );
        }

        Set<Integer> userAnswers = getCorrectAnswerIndexes(input);
        if (userAnswers.stream().anyMatch(a -> a > maxIndex || a == 0)) {
            String pattern = resourceBundleHolder.getBundle().getString("format.digits.within.range");
            throw new InputFormatException(MessageFormat.format(pattern, 1, maxIndex));
        }

        return userAnswers;
    }

    private Set<Integer> getCorrectAnswerIndexes(String answer) {
        return Arrays.stream(answer.split(ANSWERS_SEPARATOR))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toSet());
    }

}
