package com.ljt.dao;

import com.ljt.domain.Menu;

import java.util.List;

public interface MenuMapper {

    public List<Menu> findSubMenuListByPid(int pid);

    public List<Menu> findMenuByRoleId(Integer roleId);

    public List<Menu> findAllMenu();

    public Menu findMenuById(Integer id);

    public void  saveMenu(Menu menu);

    public void updateMenu(Menu menu);
}
