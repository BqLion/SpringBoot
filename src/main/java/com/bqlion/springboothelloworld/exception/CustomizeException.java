package com.bqlion.springboothelloworld.exception;

/* *
 * Created by BqLion on 2019/8/10
 */
public class CustomizeException extends  RuntimeException{
    private String message;

    public CustomizeException(IcustomizeErrorCode errorCode){
        this.message = errorCode.getMessage();

    }

    public CustomizeException(String message){
        this.message = message;
    }

    @Override
    public String getMessage(){
        return message;
    }
}
