package com.bqlion.springboothelloworld.controller;

import com.bqlion.springboothelloworld.dto.PaginationDTO;
import com.bqlion.springboothelloworld.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import javax.servlet.http.HttpServletRequest;

/* *
 * Created by BqLion on 2019/7/15
 */
@Controller
public class IndexController {

    @Autowired
    private QuestionService questionService;          //依赖注入

    @GetMapping ("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size){

        PaginationDTO pagination = questionService.list(page,size);        //这里希望返回一个QuestionDTO,一般DTO用mapper返回，
        model.addAttribute("pagination", pagination );                    //QuestionMapper只能返回Question model.
        return "index";                                                                         //加上了user的QuesitonDTO 由Question model和user model组合而成
    }
    }