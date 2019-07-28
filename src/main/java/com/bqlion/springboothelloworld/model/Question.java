package com.bqlion.springboothelloworld.model;

import lombok.Data;

/* *
 * Created by BqLion on 2019/7/26
 */
@Data

public class Question {
    private Integer id;
    private String title;
    private String description;
    private long gmtCreate;
    private long gmtModified;
    private Integer creator;
    private String tag;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
}


