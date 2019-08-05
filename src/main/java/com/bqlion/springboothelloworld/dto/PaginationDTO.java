package com.bqlion.springboothelloworld.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/* *
 * Created by BqLion on 2019/8/3
 * 展示问题页面下部的页码 与 当前页面问题 的 组装的数据结构
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> quesitions;  //当前页面问题
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;              //四个判断，确定是否显示前/后/最前/最后 四个按钮。
    private Integer page;
    private List<Integer> pages = new ArrayList<>();                  //前页/后页之间那个 1 2 3 4 5列表
    private Integer totalPage;

    public void setPagination(Integer totalPage,Integer page) {
        this.totalPage = totalPage;
        this.page = page;

        pages.add(page);
        for(int i = 1;i <= 3; i++){
            if(page - i > 0){
                pages.add(0,page - i    );          //如果page不是1,2,3，将他加入进队伍
            }
            if(page + i <= totalPage){
                pages.add(page+ i);                                         //如果page不是最后三个，也加进来
            }
        }

        //是否展示上一页
        if (page == 1) {
            showPrevious = false;
        } else {
            showPrevious = true;
        }

        if (page == totalPage) {
            showNext = false;
        } else {
            showNext = true;
        }

        if (pages.contains(1)) {
            showFirstPage = false;
        } else {
            showFirstPage = true;
        }

        if (pages.contains(totalPage)){
            showEndPage = false;
        }
            else {
                showEndPage = true;
        }
    }

}