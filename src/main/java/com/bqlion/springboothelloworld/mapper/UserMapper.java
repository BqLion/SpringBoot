package com.bqlion.springboothelloworld.mapper;

import com.bqlion.springboothelloworld.model.User;
import org.apache.ibatis.annotations.*;

/* *
 * Created by BqLion on 2019/7/22
 */
@Mapper
public interface UserMapper {

    @Insert("insert into user(name,account_id,token,gmt_create,gmt_modified,avatarUrl) values(#{name},#{accountId},#{token},#{gmtCreate},#{gmtModified},#{avatarUrl})")
    void insert(User user);

    @Select("select * from user  where token = #{token}")              //mybatis语法：在user表格里寻找token = 形参token的数据
    User  findByToken(@Param("token")String token);            //#{形参类} 就会把形参类放进括号中，如果不是类是参数，需加@param

    @Select("select * from user  where id = #{id}")
    User findById(@Param("id")Integer creator);

    @Select("select * from user  where  account_id= #{accountId}")
    User findByAccountId(@Param("accountId")String accountId);

    @Update("update user set name = #{name}, token = #{token}, gmt_modified = #{gmtModified},avatarUrl = #{avatarUrl} where id = #{id}")
    void update(User user);
}


