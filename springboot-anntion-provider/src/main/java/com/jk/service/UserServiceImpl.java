package com.jk.service;


import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.UserDao;
import com.jk.model.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    public  List<User> query(Map map){

        return userDao.query(map);

    }

    @Override
    public void deleteUser(Integer id) {
        userDao.deleteUser(id);
    }

    @Override
    public Integer queryCount(Map map) {

        return userDao.queryCount(map);
    }
}
