package com.bqlion.springboothelloworld.exception;

/* *
 * Created by BqLion on 2019/8/10
 */
public class CustomizeException extends  RuntimeException{
    private String message;
    private Integer code;

    public CustomizeException(IcustomizeErrorCode errorCode){
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage(){
        return message;
    }

    public Integer getCode() {
        return code;
    }
}
