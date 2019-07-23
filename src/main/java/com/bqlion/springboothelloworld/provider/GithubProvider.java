package com.bqlion.springboothelloworld.provider;

import com.alibaba.fastjson.JSON;
import com.bqlion.springboothelloworld.dto.AccesstokenDTO;
import com.bqlion.springboothelloworld.dto.GithubUser;
import okhttp3.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

//应该把这个类分割成两个条理才清晰，一个是gettoken，一个是getuser
/* *
 * Created by BqLion on 2019/7/17
 */
@Component

public class  GithubProvider {
    public String getAccessToken(AccesstokenDTO accesstokenDTO) {           //拿到网页url传回来的code + state ，转换成json
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(mediaType,JSON.toJSONString(accesstokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")             //用helloHttp工具把Json传给这个网址，拿到accessToken
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();

            System.out.println(string);

            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e) {                                         //返回accessToken
        //    log.error("getAccessToken error,{}", accessTokenDTO, e);
        }
         return null;
    }

    public GithubUser getUser(String accessToken){                  //上个方法拿到的accessToken
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user?access_token=" + accessToken)   //访问这个网站，用accessToekn换User信息
                .build();                                                         //卡了一天拿不到User信息就错在这个网址没有写完整
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);
            return githubUser;                                          //返回的DTo githubUser应该携带 user，id，bio三个信息，但是我这里什么也没有，出错就在这一步
        } catch (Exception e) {
          //  log.error("getUser error,{}", accessToken, e);
        }
        return null;
    }


}

