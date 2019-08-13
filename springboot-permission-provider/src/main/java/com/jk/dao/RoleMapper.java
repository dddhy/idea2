package com.jk.dao;

import com.jk.model.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

public interface RoleMapper {
    int insert(Role record);

    int insertSelective(Role record);

    @Select("select count(*) from l_role")
    Integer queryRoleCount(Map map);

    @Select("select * from l_role")
    List<Role> queryRole(Map map);
}