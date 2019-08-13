package com.jk.service;

import com.jk.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    List<User> query(Map map);

    void deleteUser(Integer id);

    Integer queryCount(Map map);



    /*    List<User> query2();*/

}
