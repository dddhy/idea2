package com.jk.dao;

import com.jk.model.Permission;
import com.jk.model.Role;
import com.jk.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserDao {

    @Select("select * from l_user where username = #{username}")
    User loginUser(String username);

    List<Permission> getTreeAll(Integer id);

    @Select("select count(*) from l_user")
    Integer queryCount(Map map);
    @Select("select * from l_user limit #{start},#{end}")
    List<User> queryUser(Map map);

    @Select("select roleid from User_Role where userid = #{userid}")
    List<String> getUserByUserid(Integer userid);

    @Select("select * from l_role")
    List<Role> getUserAll();

    @Select("select count(*) from l_permission")
    Integer queryPerCount(Map map);

    @Select("select * from l_permission limit #{start},#{end}")
    List<Permission> queryPer(Map map);

    @Select("select * from l_user")
    List<User> queryUser2();

    void addUser(List<User> list);
}
