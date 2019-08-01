package com.bqlion.springboothelloworld.service;

import com.bqlion.springboothelloworld.dto.QuestionDTO;
import com.bqlion.springboothelloworld.mapper.QuestionMapper;
import com.bqlion.springboothelloworld.mapper.UserMapper;
import com.bqlion.springboothelloworld.model.Question;
import com.bqlion.springboothelloworld.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/* *
 * Created by BqLion on 2019/7/31
 */
@Service
public class QuestionService {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;             //组装Quesiton和user的model所以要注入依赖


    public  List<QuestionDTO> list() {              //具体组装过程
    List<Question>questions  = questionMapper.list();
    List<QuestionDTO>questionDTOList = new ArrayList<>();
        for (Question question : questions) {               //遍历quesiton
            User user = userMapper.findById(question.getCreator()); //根据question的Creator来寻找
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
    }
        return questionDTOList;
    }
}
