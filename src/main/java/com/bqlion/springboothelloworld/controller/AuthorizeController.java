package com.bqlion.springboothelloworld.controller;

        import com.bqlion.springboothelloworld.dto.AccesstokenDTO;
        import com.bqlion.springboothelloworld.dto.GithubUser;
        import com.bqlion.springboothelloworld.mapper.UserMapper;
        import com.bqlion.springboothelloworld.model.User;
        import com.bqlion.springboothelloworld.provider.GithubProvider;

        import lombok.extern.slf4j.Slf4j;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.beans.factory.annotation.Value;
        import org.springframework.stereotype.Controller;
        import org.springframework.web.bind.annotation.GetMapping;
        import org.springframework.web.bind.annotation.RequestParam;

        import javax.servlet.http.Cookie;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpServletResponse;
        import java.util.UUID;


@Controller
@Slf4j
public class AuthorizeController {

        @Autowired
        private GithubProvider githubProvider;

        @Autowired
        private UserMapper UserMapper;

        @Value("0f24de62da15ff1798c1")
        private String clientId;

        @Value("82469b03c5001669927d4ce154c3f4d37c226af8")
        private String clientSecret;

        @Value("http://localhost:8080/callback")
        private String redirectUri;


        @GetMapping("/callback")
        public String callback(@RequestParam(name = "code") String code,
                               @RequestParam(name = "state") String state,
                               HttpServletResponse response) {
                AccesstokenDTO accesstokenDTO = new AccesstokenDTO();
                accesstokenDTO.setClient_id(clientId);
                accesstokenDTO.setClient_secret(clientSecret);
                accesstokenDTO.setCode(code);
                accesstokenDTO.setRedirect_uri(redirectUri);
                accesstokenDTO.setState(state);
                String accessToken = githubProvider.getAccessToken(accesstokenDTO);
                GithubUser githubUser = githubProvider.getUser(accessToken);
                if (githubUser != null) {
                        UserMapper.insert(new User());
                        User user = new User();
                        String token = UUID.randomUUID().toString();
                        user.setToken(token);
                        user.setName(githubUser.getName());
                        user.setAccountId(String.valueOf(githubUser.getId()));
                        user.setGmtCreate(System.currentTimeMillis());
                        user.setGmtModified(user.getGmtCreate());
                        UserMapper.insert(user);
                        Cookie cookie = new Cookie("token", token);
                        cookie.setMaxAge(60 * 60 * 24 * 30 * 6);

                        response.addCookie(cookie);
                        System.out.println(user.getName());
                        System.out.println(githubUser.getName());
                        return "redirect:/";

                } else {
//                        log.error("callback get github error,{}", githubUser);
                        // 登录失败，重新登录
                        System.out.println("NO");
                        return "redirect:/";
                }
        }

        @GetMapping("/logout")
        public String logout(HttpServletRequest request,
                             HttpServletResponse response) {
                request.getSession().removeAttribute("user");
                Cookie cookie = new Cookie("token", null);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
                return "redirect:/";
        }
}