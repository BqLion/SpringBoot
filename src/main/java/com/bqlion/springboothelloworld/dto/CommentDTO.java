package com.bqlion.springboothelloworld.dto;

import lombok.Data;

/* *
 * Created by BqLion on 2019/8/12
 */
@Data
public class CommentDTO {
    private long parentId;
    private String content;
    private Integer type;
}
