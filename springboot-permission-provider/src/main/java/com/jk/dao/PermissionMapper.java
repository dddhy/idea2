package com.jk.dao;

import com.jk.model.Permission;

public interface PermissionMapper {
    int insert(Permission record);

    int insertSelective(Permission record);
}