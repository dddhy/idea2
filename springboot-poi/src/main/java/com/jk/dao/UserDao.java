package com.jk.dao;

import com.jk.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserDao {

    @Select("select count(*) from t_user")
    Integer queryCount(Map map);

    List<User> query(Map map);

    @Select("select * from t_user")
    List<User> query2();

    void addUser(List<User> list);

    List<User> query3();
}
