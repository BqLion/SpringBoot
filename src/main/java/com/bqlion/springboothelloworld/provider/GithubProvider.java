package com.bqlion.springboothelloworld.provider;

import com.bqlion.springboothelloworld.dto.AccesstokenDTO;
import com.bqlion.springboothelloworld.dto.GithubUser;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;


/* *
 * Created by BqLion on 2019/7/17
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccesstokenDTO accesstokenDTO) {
        MediaType JSON = MediaType.get("application/json; charset=utf-8");

        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            System.out.println(string);
            return string;
        } catch (IOException e) {
        }
         return null;
    }

    public GithubUser getUser(String accessToken){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();

    }


}
