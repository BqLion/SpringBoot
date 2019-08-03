package com.bqlion.springboothelloworld.service;

import com.bqlion.springboothelloworld.dto.PaginationDTO;
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



    public PaginationDTO list(Integer page, Integer size) {
    Integer offset = size * (page - 1);
    List<Question>questions  = questionMapper.list(offset,size);
    List<QuestionDTO>questionDTOList = new ArrayList<>();

    PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {               //这个循环完成question与questionDTO的组装
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
    }

        paginationDTO.setQuesitions(questionDTOList);   //questionDTO与paginationDTO的组装
        Integer totalCount = questionMapper.count();
        paginationDTO.setPagination(totalCount,page,size);  //如何生产出一个paginiationDTO？专门在数据结构中写一个set函数
                                                                                                                                            //这个set函数只需要总数据数，页码和每页个数
                                                                                                                                            //便可生成所有所需数据
                                                                                                                                            //数据层面的逻辑在数据内部写清楚，不要拿到业务层面来
        return paginationDTO;
    }
}
