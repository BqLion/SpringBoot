package com.bqlion.springboothelloworld.mapper;

import com.bqlion.springboothelloworld.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/* *
 * Created by BqLion on 2019/7/22
 */
@Mapper
public interface UserMapper {
    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified})")
    void insert(User user);
}

