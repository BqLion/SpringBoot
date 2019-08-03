package com.bqlion.springboothelloworld.mapper;

/* *
 * Created by BqLion on 2019/7/26
 * mybatis语句：@这一行是mysql预计
 * 下边是可供本项目调用的函数名称
 */

import com.bqlion.springboothelloworld.model.Question;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface QuestionMapper {
    @Insert("insert into question(title,description,gmt_create,gmt_modified,creator,tag) values (#{title},#{description},#{gmtCreate},#{gmtModified},#{creator},#{tag})")
    void create(Question question);

    @Select("select * from question limit #{offset},#{size}")           //从偏移量offset开始，输出size个数据
    List<Question> list(Integer offset, Integer size);

    @Select("select count(1) from question")
    Integer count();                                                                        //计数函数，可返回数据库元素个数
}