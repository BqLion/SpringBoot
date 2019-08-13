package com.bqlion.springboothelloworld.enums;

/* *
 * Created by BqLion on 2019/8/12
 */
public enum CommentTypeEnum {
    QUESTION(1),
    COMMENT(2);

    private Integer type;

    public Integer getType() {
        return type;
    }

    CommentTypeEnum(Integer type){
        this.type = type;
    }
}
