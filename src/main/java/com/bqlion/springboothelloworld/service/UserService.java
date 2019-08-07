package com.bqlion.springboothelloworld.controller;

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


    public static void createOrUpdate(User user) {
        User dbUser =new User();
        dbUser =  userMapper.findByAccountId(user.getAccountId());
    }
}
