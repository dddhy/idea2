package com.jk.service.impl;

import com.jk.dao.UserDao;
import com.jk.model.User;
import com.jk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Integer queryCount(Map map) {
        return userDao.queryCount(map);
    }

    @Override
    public List<User> query(Map map) {
        return userDao.query(map);
    }

    @Override
    public List<User> query2() {
        return userDao.query2();
    }

    @Override
    public void addUser(List<User> list) {
        userDao.addUser(list);
    }

    @Override
    public List<User> query3() {
        return userDao.query3();
    }


}
