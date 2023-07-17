package com.ljt.service.impl;

import com.ljt.dao.RoleMapper;
import com.ljt.domain.Role;
import com.ljt.domain.RoleMenuVo;
import com.ljt.domain.Role_menu_relation;
import com.ljt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class RoleServiceimpl implements RoleService {
    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        List<Role> allRole = roleMapper.findAllRole(role);
        return allRole;
    }

    @Override
    public void saveRole(Role role) {
        role.setCreatedBy("System");
        role.setUpdatedBy("System");
        role.setCreatedTime(new Date());
        role.setUpdatedTime(new Date());
        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        role.setUpdatedTime(new Date());
        roleMapper.updateRole(role);
    }

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {
        List<String> list = roleMapper.findMenuByRoleId(roleId);
        return list;
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {
        deleteRoleContextMenu(roleMenuVo.getRoleId());
        for (Integer integer : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setMenuId(integer);
            role_menu_relation.setCreatedTime(new Date());
            role_menu_relation.setUpdatedTime(new Date());
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            roleMapper.RoleContextMenu(role_menu_relation);

        }
    }

    @Override
    public void deleteRoleContextMenu(int roleId) {
        roleMapper.deleteRoleContextMenu(roleId);
    }

    @Override
    public void deleteRole(Integer id) {
        deleteRoleContextMenu(id);
        roleMapper.deleteRole(id);
    }
}
