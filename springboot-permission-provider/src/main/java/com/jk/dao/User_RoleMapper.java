package com.jk.dao;

import com.jk.model.User_Role;
import org.apache.ibatis.annotations.Delete;

public interface User_RoleMapper {
    int insert(User_Role record);

    int insertSelective(User_Role record);

    @Delete("delete from user_role where userid=#{userid}")
    int deleteRolePer(Integer userid);
}