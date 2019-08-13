package com.jk.dao;

import com.jk.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface UserDao {


    List<User> query(Map map);

    @Select("delete from t_user where userid = #{id}")
    void deleteUser(Integer id);
    @Select("select count(*) from t_user")
    Integer queryCount(Map map);
}
