package com.jk.dao;

import com.jk.model.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserDao {

 /*   @Select("select * from t_user")*/
    List<User> query();

}
