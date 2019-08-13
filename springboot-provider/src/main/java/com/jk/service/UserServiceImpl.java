package com.jk.service;

import com.jk.dao.UserDao;
import com.jk.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> query(Map map) {
        System.err.println(123);
        return userDao.query();

    }

    @Override
    public void deleteUser(Integer id) {

    }

    @Override
    public Integer queryCount(Map map) {
        return null;
    }


}
