package com.bqlion.springboothelloworld.dto;

/* *
 * Created by BqLion on 2019/7/18
 */
import lombok.Data;

@Data
public class GithubUser {
    private String name;
    private Long id;
    private String bio;
    private String avatarurl;
}
