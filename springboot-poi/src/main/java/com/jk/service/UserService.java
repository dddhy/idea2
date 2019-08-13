package com.jk.service;

import com.jk.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    Integer queryCount(Map map);

    List<User> query(Map map);

    List<User> query2();

    void addUser(List<User> list);

    List<User> query3();
}
