package com.bqlion.springboothelloworld.exception;

import javax.swing.plaf.IconUIResource;

/* *
 * Created by BqLion on 2019/8/10
 */
public enum CustomizeErrorCode implements IcustomizeErrorCode {
    QUESTION_NOT_FOUND("问题不存在，换个试试？");

    @Override
    public String getMessage() {
        return message;
    }

    private String message;

    CustomizeErrorCode(String message){
        this.message = message;
    }
}
