package com.bqlion.springboothelloworld.controller;

import com.bqlion.springboothelloworld.dto.CommentDTO;
import com.bqlion.springboothelloworld.mapper.CommentMapper;
import com.bqlion.springboothelloworld.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/* *
 * Created by BqLion on 2019/8/12
 */
@Controller
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;

    @ResponseBody
    @RequestMapping(value = "/comment",method =  RequestMethod.POST)
    public Object post(@RequestBody CommentDTO commentDTO){             //commentDTO是前端传过来的JSON
        Comment comment = new Comment();
        comment.setParentId(commentDTO.getParentId());
        comment.setContent(commentDTO.getContent());
        comment.setType(commentDTO.getType());
        comment.setGmtModified(System.currentTimeMillis());
        comment.setGmtCreate(System.currentTimeMillis());
        comment.setCommentator(1);
        commentMapper.insert(comment);                                                       //comment是后端的对象，往数据库里写入此数据
        return null;
    }
}
