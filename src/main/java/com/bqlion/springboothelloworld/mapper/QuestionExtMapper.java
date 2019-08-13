package com.bqlion.springboothelloworld.mapper;

import com.bqlion.springboothelloworld.dto.QuestionQueryDTO;
import com.bqlion.springboothelloworld.model.Question;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

    int incCommentCount(Question record);

    List<Question> selectRelated(Question question);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}