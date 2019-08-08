package com.bqlion.springboothelloworld.service;

import com.bqlion.springboothelloworld.mapper.UserMapper;
import com.bqlion.springboothelloworld.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/* *
 * Created by BqLion on 2019/8/7
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;


    public void createOrUpdate(User user) {
        User dbUser = userMapper.findByAccountId(user.getAccountId());
        if(dbUser == null){
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            userMapper.insert(user);                                    //如果数据库没有，则新建
        }
        else{
            dbUser.setGmtModified(System.currentTimeMillis());
            dbUser.setAvatarUrl(user.getAvatarUrl());
            dbUser.setName(user.getName());
            dbUser.setToken(user.getToken());                   //数据库有，则将可能更新的元素check一遍
            userMapper.update(dbUser);
        }
    }
}
