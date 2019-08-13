package com.jk.dao;

import com.jk.model.Permission;
import com.jk.model.Role_Per;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface Role_PerMapper {
    int insert(Role_Per record);

    int insertSelective(Role_Per record);

    @Select("select perid from role_permission where roleid = #{roleid}")
    List<String> getPerByid(Integer roleid);

    @Select("select * from l_permission")
    List<Permission> getPerAll();

    @Delete("delete from role_permission where roleid = #{roleid}")
    int deleteRolePer(Integer roleid);
}