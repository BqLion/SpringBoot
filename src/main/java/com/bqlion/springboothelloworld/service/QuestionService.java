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

import java.net.Inet4Address;
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
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.count();


        if(totalCount % size == 0){
            totalPage  = totalCount / size;
        }
        else
        {
            totalPage = totalCount / size + 1;
        }

        if(page < 1 ){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage,page);

        Integer offset = size * (page - 1);
        List<Question>questions  = questionMapper.list(offset,size);
        List<QuestionDTO>questionDTOList = new ArrayList<>();

        for (Question question : questions) {               //这个循环完成question与questionDTO的组装
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
    }
        paginationDTO.setQuesitions(questionDTOList);  //如何生产出一个paginiationDTO？专门在数据结构中写一个set函数

        return paginationDTO;
    }

    public PaginationDTO list(Integer userId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();
        Integer totalPage;
        Integer totalCount = questionMapper.countByUserId(userId);


        if (totalCount % size == 0) {
            totalPage = totalCount / size;
        } else {
            totalPage = totalCount / size + 1;
        }

        if (page < 1) {
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        paginationDTO.setPagination(totalPage, page);

        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.listByUserId(userId, offset, size);
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {               //这个循环完成question与questionDTO的组装
            User user = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }
        paginationDTO.setQuesitions(questionDTOList);   //questionDTO与paginationDTO的组装
        return paginationDTO;
    }
        public QuestionDTO getById(Integer id){
            Question question =  questionMapper.getById(id);
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            User user = userMapper.findById(question.getCreator());
            questionDTO.setUser(user);
            return questionDTO;

    }

    public void createOrUpdate(Question question) {
    if(question.getId() == null){
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        }
        else{
        question.setGmtModified(question.getGmtCreate());
        questionMapper.update(question);
        }
    }
}
