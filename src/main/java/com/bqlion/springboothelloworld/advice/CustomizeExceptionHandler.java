package com.bqlion.springboothelloworld.advice;

import com.bqlion.springboothelloworld.exception.CustomizeException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

/* *
 * Created by BqLion on 2019/8/10
 */
@ControllerAdvice()
public class CustomizeExceptionHandler {
        @ExceptionHandler(Exception.class)
        ModelAndView handle(Throwable e, Model model) {
            if(e instanceof CustomizeException) {
                model.addAttribute("message", e.getMessage());
            }else{
                model.addAttribute("message","服务器冒烟了换个试试！！");
            }
            return new ModelAndView("error");
        }
    }
