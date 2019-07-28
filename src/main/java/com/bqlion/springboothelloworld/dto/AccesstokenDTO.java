package com.bqlion.springboothelloworld.dto;
import lombok.Data;
/* *
 * Created by BqLion on 2019/7/17
 */
@Data
public class AccesstokenDTO {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}