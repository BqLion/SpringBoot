package com.bqlion.springboothelloworld.controller;

import com.bqlion.springboothelloworld.cache.TagCache;
import com.bqlion.springboothelloworld.dto.QuestionDTO;
import com.bqlion.springboothelloworld.model.Question;
import com.bqlion.springboothelloworld.model.User;
import com.bqlion.springboothelloworld.service.QuestionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;


/* *
 * Created by BqLion on 2019/7/24
 */
@Controller
public class PublishController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/publish/{id}")
    public String edit(@PathVariable(name = "id")Long id,
                       Model model){
        QuestionDTO  question = questionService.getById(id);
        model.addAttribute("title",question.getTitle());
        model.addAttribute("description",question.getDescription());
        model.addAttribute("tag",question.getTag());
        model.addAttribute("id",question.getId());
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }

    @GetMapping("/publish")
    public String publish(Model model){
        model.addAttribute("tags", TagCache.get());
        return "publish";
    }


    @PostMapping("/publish")
    public String doPublish(
            @RequestParam(value = "title",required = false) String title,
            @RequestParam(value = "description",required = false) String description,
            @RequestParam(value = "tag",required = false)String tag,
            @RequestParam(value = "id",required = false)String idOrNull,
            HttpServletRequest request,
            Model model) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag",tag);
        model.addAttribute("tags", TagCache.get());
    //    model.addAttribute("id", id);

        if(title == null || title== "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if(description == null || description== "") {
            model.addAttribute("error", "标题不能为空");
            return "publish";
        }

        if(tag == null || tag=="") {
            model.addAttribute("error", "标签不能为空");
            return "publish";
        }

        String invalid = TagCache.filterInvalid(tag);
        if (StringUtils.isNotBlank(invalid)) {
            model.addAttribute("error", "输入非法标签:" + invalid);
            return "publish";
        }

        User user = (User) request.getSession().getAttribute("user");
        if (user == null) {
            model.addAttribute("error", "用户未登录");
            return "publish";
        }

        Question question = new Question();     //question对象接受各种数据
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        if(idOrNull != ""){
            question.setId(Long.parseLong(idOrNull));
        }
        questionService.createOrUpdate(question);
        return "redirect:/";                //发布成功就返回首页
    }
}
