package com.otus.spring.dao.api;

import com.otus.spring.model.Question;

import java.util.List;

public interface QuestionsDao {

    List<Question> readQuestions();

}
