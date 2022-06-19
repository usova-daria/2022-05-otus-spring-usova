package com.otus.spring.dao.validator;

import com.otus.spring.exception.ValidationError;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.otus.spring.dao.mapper.QuestionMapper.CORRECT_ANSWER_SEPARATOR;

public class QuestionValidator {

    /**
     * A question (1) must have at least (2) answers. The last entry is reserved for an answer
     */
    private final static int MIN_NUMBER_OF_COLUMNS = 1 + 2 + 1;

    private final static String CORRECT_ANSWER_REGEX = "(\\d+" + CORRECT_ANSWER_SEPARATOR + "*)+";
    private final static Pattern CORRECT_ANSWER_PATTERN = Pattern.compile(CORRECT_ANSWER_REGEX);

    public void validate(List<String> entries) {
        checkNumberOfEntries(entries);
        validateCorrectAnswer( entries.get( entries.size() - 1 ), entries.size() - 2);
    }

    private void checkNumberOfEntries(List<String> entries) {
        if (entries.size() < MIN_NUMBER_OF_COLUMNS) {
            throw new ValidationError("There should be at least " + MIN_NUMBER_OF_COLUMNS + " columns (" + entries + ")");
        }
    }

    private void validateCorrectAnswer(String correctAnswer, int numberOfAnswers) {
        Matcher matcher = CORRECT_ANSWER_PATTERN.matcher(correctAnswer);
        if (!matcher.matches()) {
            throw new ValidationError("Wrong correct answer format: " + correctAnswer);
        }

        if (Arrays.stream(correctAnswer.split(CORRECT_ANSWER_SEPARATOR))
                .map(Integer::parseInt)
                .anyMatch(i -> i >= numberOfAnswers)) {
            throw new ValidationError("There is a correct answer out of correct answers range. Answers should be between 1 and " + numberOfAnswers);
        }
    }

}
