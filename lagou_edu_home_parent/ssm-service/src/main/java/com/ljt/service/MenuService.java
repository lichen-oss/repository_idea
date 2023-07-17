package com.ljt.service;

import com.ljt.domain.Menu;

import java.util.List;

public interface MenuService {
    public List<Menu> findSubMenuListByPid(int pid);

    public List<Menu> findAllMenu();

    public Menu findMenuById(Integer id);

    public void  saveMenu(Menu menu);

    public void updateMenu(Menu menu);



}
