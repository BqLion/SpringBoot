package com.bqlion.springboothelloworld.controller;

import com.bqlion.springboothelloworld.mapper.UserMapper;
import com.bqlion.springboothelloworld.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/* *
 * Created by BqLion on 2019/7/15
 */
@Controller
public class IndexController {

    @Autowired
    private UserMapper UserMapper;

    @GetMapping ("/")
    public String index(HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
       if(cookies != null) {                                            //加一句判断。昨天正常今天就不能访问主页了，抛nullpointer exception。
            for (Cookie cookie : cookies) {                      //估计是清理cookies后首次访问没有cookies，所以会抛出异常。
                if (cookie.getName().equals("token")) {     //加后解决
                    String token = cookie.getValue();
                    User user = UserMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }
        return "index";
    }
    }