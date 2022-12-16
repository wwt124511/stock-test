package com.wwt.stocktest.service.impl;

import com.wwt.stocktest.domain.User;
import com.wwt.stocktest.mapper.UserMapper;
import com.wwt.stocktest.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wwt
 * @ClassName UserServiceImpl.java
 * @Description TODO
 * @createTime 2022-12-11 00:08
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> selectAll() {
        List<User> userList = userMapper.selectList(null);
        return userList;
    }
}
