package com.bqlion.springboothelloworld.dto;

import lombok.Data;

import java.util.List;

/* *
 * Created by BqLion on 2019/8/14
 */
    @Data
    public class TagDTO {
        private String categoryName;
        private List<String> tags;
    }

