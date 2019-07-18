package com.bqlion.springboothelloworld.controller;
import com.bqlion.springboothelloworld.dto.AccesstokenDTO;
import com.bqlion.springboothelloworld.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

/* *
 * Created by BqLion on 2019/7/17
 */
@Controller
public class AuthorizeController {
        @Autowired
        private GithubProvider githubProvider;

        @GetMapping("/callback")
        public String callback(@RequestParam(name = "code")String code,
                               @RequestParam(name = "state")String state){
                AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
                accesstokenDTO.setCode(code);
                accesstokenDTO.setRedirect_url("https://localhost:8080/callback");
                accesstokenDTO.setState(state);
                accesstokenDTO.setClient_id("0f24de62da15ff1798c1");
                accesstokenDTO.setClient_secret("82469b03c5001669927d4ce154c3f4d37c226af8");
                githubProvider.getAccessToken(accesstokenDTO);
        return "index";
        }
}
