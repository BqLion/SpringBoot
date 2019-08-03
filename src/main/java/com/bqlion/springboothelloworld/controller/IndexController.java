package com.bqlion.springboothelloworld.controller;

import com.bqlion.springboothelloworld.dto.PaginationDTO;
import com.bqlion.springboothelloworld.dto.QuestionDTO;
import com.bqlion.springboothelloworld.mapper.QuestionMapper;
import com.bqlion.springboothelloworld.mapper.UserMapper;
import com.bqlion.springboothelloworld.model.Question;
import com.bqlion.springboothelloworld.model.User;
import com.bqlion.springboothelloworld.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/* *
 * Created by BqLion on 2019/7/15
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private QuestionService questionService;          //依赖注入

    @GetMapping ("/")
    public String index(HttpServletRequest request,
                        Model model,
                        @RequestParam(name = "page",defaultValue = "1")Integer page,
                        @RequestParam(name = "size",defaultValue = "5")Integer size){
        Cookie[] cookies = request.getCookies();
       if(cookies != null && cookies.length != 0)                                             //加一句判断。昨天正常今天就不能访问主页了，抛nullpointer exception。
            for (Cookie cookie : cookies) {                      //估计是清理cookies后首次访问没有cookies，所以会抛出异常。
                if (cookie.getName().equals("token")) {     //加后解决
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        PaginationDTO paginationDTO = questionService.list(page,size);        //这里希望返回一个QuestionDTO,一般DTO用mapper返回，
        model.addAttribute("paginationDTO", paginationDTO);                    //QuestionMapper只能返回Question model.
        return "index";                                                                         //加上了user的QuesitonDTO 由Question model和user model组合而成
    }
    }