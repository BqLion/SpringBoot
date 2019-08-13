package com.bqlion.springboothelloworld.dto;

import com.bqlion.springboothelloworld.model.User;
import lombok.Data;

/* *
 * Created by BqLion on 2019/8/12
 */
@Data

public class CommentDTO {
    private Long id;
    private Long parentId;
    private Integer type;
    private Long commentator;
    private Long gmtCreate;
    private Long gmtModified;
    private Long likeCount;
    private Integer commentCount;
    private String content;
    private User user;
}