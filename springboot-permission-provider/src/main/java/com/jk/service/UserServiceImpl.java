package com.jk.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.jk.dao.RoleMapper;
import com.jk.dao.Role_PerMapper;
import com.jk.dao.UserDao;
import com.jk.dao.User_RoleMapper;
import com.jk.model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService{


    @Autowired
    private UserDao userDao;

    @Autowired
    private User_RoleMapper user_roleMapper;

    @Autowired
    private Role_PerMapper role_PerMapper;

    @Autowired
    private RoleMapper roleMapper;
    @Override
    public User loginUser(User user) {

        User user2 = userDao.loginUser(user.getUsername());

        return user2;
    }

    @Override
    public List<Permission> getTreeAll(Integer id) {
        return userDao.getTreeAll(id);
    }



    @Override
    public Integer queryCount(Map map) {
        return userDao.queryCount(map);
    }

    @Override
    public List<User> queryUser(Map map) {

        return userDao.queryUser(map);
    }

    @Override
    public List<Role> getUserByUserid(Integer userid) {

        List<String> list = userDao.getUserByUserid(userid);

        List<Role> list2 = userDao.getUserAll();

        for (int i =0;i<list.size();i++){
            for (int j=0;j<list2.size();j++){
                if(list.get(i).equals(list2.get(j).getId().toString())){
                    list2.get(j).setChecked("true");
                }
            }
        }
        return list2;
    }

    @Override
    public int updateUserRole(Integer[] roleids, Integer userid) {

        int i = user_roleMapper.deleteRolePer(userid);

        if (i >= 0){
            for (int j=0; j<roleids.length; j++){
                User_Role ur = new User_Role();
                ur.setRoleid(roleids[j]);
                ur.setUserid(userid);

                i = user_roleMapper.insert(ur);
            }
        }

        return i;
    }

    @Override
    public Integer queryRoleCount(Map map) {
        return roleMapper.queryRoleCount(map);
    }

    @Override
    public List<Role> queryRole(Map map) {

        return roleMapper.queryRole(map);
    }

    @Override
    public List<Permission> getPerByid(Integer roleid) {
        //查出 关联表 id
        List<String> list = role_PerMapper.getPerByid(roleid);

        //
        List<Permission> list2 = role_PerMapper.getPerAll();

        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list2.size(); j++) {
                if(list.get(i).equals(list2.get(j).getId().toString())){
                    list2.get(j).setChecked("true");
                }
            }
        }
        return list2;
    }

    @Override
    public int updateRolePremiss(Integer[] perids, Integer roleid) {

        //先删除 关联表 原有 id
        int i = role_PerMapper.deleteRolePer(roleid);

        if(i >= 0){

            for (int j = 0; j < perids.length; j++) {
                Role_Per rpm = new Role_Per();
                rpm.setPerid(perids[j]);
                rpm.setRoleid(roleid);

                i = role_PerMapper.insert(rpm);
            }
        }
        return i;
    }

    @Override
    public Integer queryPerCount(Map map) {
        return userDao.queryPerCount(map);
    }

    @Override
    public List<Permission> queryPer(Map map) {

        return userDao.queryPer(map);
    }

    @Override
    public List<User> queryUser2() {
        return userDao.queryUser2();
    }

    @Override
    public void addUser(List<User> list) {
        userDao.addUser(list);
    }


}
