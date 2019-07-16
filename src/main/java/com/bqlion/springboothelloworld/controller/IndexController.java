package com.bqlion.springboothelloworld.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/* *
 * Created by BqLion on 2019/7/15
 */
@Controller
public class IndexController {
    @GetMapping("/templates")
    public String index(){return "index"; }
}
