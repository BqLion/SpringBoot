package com.bqlion.springboothelloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/* *
 * Created by BqLion on 2019/7/24
 */
@Controller
public class PublishController {
    @GetMapping("/publish")
    public String publish(){
        return "publish";

    }
}
