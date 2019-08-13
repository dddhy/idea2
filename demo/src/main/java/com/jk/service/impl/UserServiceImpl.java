package com.jk.service.impl;




import com.jk.dao.UserMapper;
import com.jk.model.Tree;
import com.jk.model.User;
import com.jk.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {


    @Resource
    private UserMapper userMapper;

    @Override
    public List<User> query() {
        return userMapper.query();
    }

    @Override
    public void deleteUser(String id) {

        userMapper.deleteUser(id);
    }

    @Override
    public void addUser(User user) {

        userMapper.addUser(user);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    @Override
    public List<Tree> getTreeAll() {
        return userMapper.getTreeAll();
    }


}
