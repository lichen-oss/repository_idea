package com.ljt.dao;

import com.ljt.domain.Role;
import com.ljt.domain.Role_menu_relation;

import java.util.List;

public interface RoleMapper {
    //查询角色
    public List<Role> findAllRole(Role role);

    public void updateRole(Role role);

    public void saveRole(Role role);

    public List<String> findMenuByRoleId(Integer roleid);

    void RoleContextMenu(Role_menu_relation role_menu_relation);

    void deleteRoleContextMenu(int roleId);

    void deleteRole(Integer id);


}
