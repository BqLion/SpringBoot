package com.bqlion.springboothelloworld.dto;

import com.bqlion.springboothelloworld.model.User;
import lombok.Data;
import org.omg.PortableInterceptor.INACTIVE;

/* *
 * Created by BqLion on 2019/7/31
 */
@Data
public class QuestionDTO {
    private Long  id;
    private String title;
    private String  description;
    private String tag;
    private long gmtCreate;
    private long gmtModified;
    private Long creator;
    private Integer viewCount;
    private Integer commentCount;
    private Integer likeCount;
    private User user;          //传输层的Qusition作用就是比model的question多了一个user对象
}
