package com.otus.spring.dao.impl;

import com.otus.spring.exception.ValidationError;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class CsvRowParser {

    /**
     * If token starts and ends with quotes it must not contain quotes inside
     */
    private final static String TOKEN_WITH_QUOTES = "\"[^\"]+\"\\s*,?";

    private final static String TOKEN_WITHOUT_QUOTES = "[^\"]+,?";

    private final static String CSV_ROW_REGEX = "(" + TOKEN_WITH_QUOTES + "|" + TOKEN_WITHOUT_QUOTES + ")+";

    private final static Pattern CSV_ROW_PATTERN = Pattern.compile(CSV_ROW_REGEX);

    private final static String SEPARATOR = ",";

    private final static char ESCAPE_CHARACTER = '"';

    private final static String ESCAPE_STRING = "" + ESCAPE_CHARACTER;

    public List<String> parse(String csvRow) {
        preValidateCsvRow(csvRow);

        List<String> tokens = Arrays.stream(csvRow.split(SEPARATOR))
                .map(String::trim)
                .collect(Collectors.toList());

        return parseTokens(tokens);
    }

    private void preValidateCsvRow(String question) {
        if (question == null) {
            throw new ValidationError("Question must not be null");
        }

        Matcher matcher = CSV_ROW_PATTERN.matcher(question);
        if (!matcher.matches()) {
            throw new ValidationError("Question format is not correct: " + question);
        }

    }

    private List<String> parseTokens(List<String> tokens) {
        List<String> entries = new ArrayList<>();

        StringBuilder questionOrAnswer = new StringBuilder();
        for (String token : tokens) {
            if (token.isEmpty()) {
                continue;
            }

            questionOrAnswer.append(token);
            if (isPartOfEntry(questionOrAnswer.toString())) {
                questionOrAnswer.append(SEPARATOR).append(" ");
                continue;
            }

            var newEntry = questionOrAnswer.toString();
            if (newEntry.startsWith(ESCAPE_STRING) && newEntry.endsWith(ESCAPE_STRING)) {
                newEntry = newEntry.substring(1, newEntry.length() - 1);
            }

            entries.add(newEntry);
            questionOrAnswer = new StringBuilder();
        }

        return entries;
    }

    private boolean isPartOfEntry(String token) {
        return token.startsWith(ESCAPE_STRING) && !token.endsWith(ESCAPE_STRING);
    }

}
