package com.bqlion.springboothelloworld.controller;

import com.bqlion.springboothelloworld.mapper.QuestionMapper;
import com.bqlion.springboothelloworld.mapper.UserMapper;
import com.bqlion.springboothelloworld.model.Question;
import com.bqlion.springboothelloworld.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/* *
 * Created by BqLion on 2019/7/24
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionMapper QuestionMapper;

    @Autowired
    private UserMapper UserMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model) {

        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);

        if(title == null || title== "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if(title == null || title== "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if(title == null || title== "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }
        /**
         * 以上是把各种参数为空的可能性都排除掉，类似于提前排除掉数组为空的bug
         */

        Question question = new Question();     //question对象接受各种数据
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        QuestionMapper.create(question);          //mapper把数据写入数据库
        return "redirect:/";                //发布成功就返回首页
    }
}
