package com.ljt.service;

import com.ljt.domain.Role;
import com.ljt.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    public List<Role> findAllRole(Role role);

    public void saveRole(Role role);

    public void updateRole(Role role);

    public List<String> findMenuByRoleId(Integer roleId);

    public void RoleContextMenu(RoleMenuVo roleMenuVo);

    public void deleteRoleContextMenu(int roleId);

    public void deleteRole(Integer id);
}
