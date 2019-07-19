package com.bqlion.springboothelloworld.controller;
import com.bqlion.springboothelloworld.dto.AccesstokenDTO;
import com.bqlion.springboothelloworld.dto.GithubUser;
import com.bqlion.springboothelloworld.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/* *
 * Created by BqLion on 2019/7/17
 */
@Controller
public class AuthorizeController {
        @Autowired
        private GithubProvider githubProvider;

        @Value("$(github.clinet.id)")           //把配置文件中的value传过来
        private String clientId;
        @Value("$(github.clinet.secret)")
        private String clinetSercet;
        @Value("$(github.clinet.Redirect_url)")
        private String clinetRedirect_url;

        @GetMapping("/callback")
        public String callback(@RequestParam(name = "code")String code,
                               @RequestParam(name = "state")String state,
                               HttpServletRequest request){
                AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
                accesstokenDTO.setCode(code);
                accesstokenDTO.setRedirect_url(clinetRedirect_url);
                accesstokenDTO.setState(state);
                accesstokenDTO.setClient_id(clientId);
                accesstokenDTO.setClient_secret(clinetSercet);
                String accessToken = githubProvider.getAccessToken(accesstokenDTO);
                GithubUser user = githubProvider.getUser(accessToken);
                //System.out.println(user.getName());

                if(user!=null){
                        //登录成功，写cookie和session
                        request.getSession().setAttribute("user",user);
                        return "redirect:index";
                }else{
                        return "redirect:index";
                        //登录失败，重新登录
                }

        }
}
