package com.jk.service;

import com.jk.model.Tree;
import com.jk.model.User;

import java.util.List;

public interface UserService {
    List<User> query();

    void deleteUser(String id);

    void addUser(User user);

    void updateUser(User user);

    List<Tree> getTreeAll();
}
