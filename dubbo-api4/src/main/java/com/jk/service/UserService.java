package com.jk.service;

import com.jk.model.Permission;
import com.jk.model.Role;
import com.jk.model.User;

import java.util.List;
import java.util.Map;

public interface UserService {

    User loginUser(User user);

    List<Permission> getTreeAll(Integer id);

    Integer queryCount(Map map);

    List<User> queryUser(Map map);

    List<Role> getUserByUserid(Integer userid);

    int updateUserRole(Integer[] roleids, Integer userid);

    Integer queryRoleCount(Map map);

    List<Role> queryRole(Map map);

    List<Permission> getPerByid(Integer roleid);

    int updateRolePremiss(Integer[] perids, Integer roleid);

    Integer queryPerCount(Map map);

    List<Permission> queryPer(Map map);

    List<User> queryUser2();

    void addUser(List<User> list);
}
