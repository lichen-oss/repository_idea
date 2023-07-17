package com.ljt.service.impl;

import com.ljt.dao.MenuMapper;
import com.ljt.domain.Menu;
import com.ljt.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class MenuServiceimpl implements MenuService {
@Autowired
    private MenuMapper menuMapper;
    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);
        return subMenuListByPid;
    }

    @Override
    public List<Menu> findAllMenu() {
        List<Menu> allMenu = menuMapper.findAllMenu();
        return allMenu;
    }

    @Override
    public Menu findMenuById(Integer id) {
        Menu menu = menuMapper.findMenuById(id);
        return menu;
    }

    @Override
    public void saveMenu(Menu menu) {
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        menu.setCreatedTime(new Date());
        menu.setUpdatedTime(new Date());
        menuMapper.saveMenu(menu);
        System.out.println(menu.toString());
    }

    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedBy("system");
        menu.setUpdatedTime(new Date());
        menuMapper.updateMenu(menu);
    }


}
