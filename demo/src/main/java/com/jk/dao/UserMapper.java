package com.jk.dao;

import com.jk.model.Tree;
import com.jk.model.User;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> query();

    void deleteUser(String id);

    void addUser(User user);

    void updateUser(User user);

    @Select("select * from t_tree")
    List<Tree> getTreeAll();
}