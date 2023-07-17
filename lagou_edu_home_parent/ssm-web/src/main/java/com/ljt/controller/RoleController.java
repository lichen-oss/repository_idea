package com.ljt.controller;

import com.ljt.domain.Menu;
import com.ljt.domain.ResponseResult;
import com.ljt.domain.Role;
import com.ljt.domain.RoleMenuVo;
import com.ljt.service.MenuService;
import com.ljt.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequestMapping("/role")
@RestController
public class RoleController {
    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllUserByPage(@RequestBody Role role) {
        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true, 200, "响应成功", allRole);

    }

    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role) {
        try {
            if (role.getId() == null) {
                roleService.saveRole(role);
                return new ResponseResult(true, 200, "添加成功", "");
            } else {
                roleService.updateRole(role);
                return new ResponseResult(true, 200, "修改成功", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu() {
        List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
        Map<String, Object> map = new HashMap<>();
        map.put("parentMenuList", subMenuListByPid);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", map);
        return result;

    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(@RequestParam int roleId) {
        List<String> menuList = roleService.findMenuByRoleId(roleId);
        ResponseResult result = new ResponseResult(true, 200, "响应成功", menuList);
        return result;
    }
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo) {
        roleService.RoleContextMenu(roleMenuVo);
        return new ResponseResult(true, 200, "响应成功", "");
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        ResponseResult responseResult = new ResponseResult(true,200,"响应成功","");
        return  responseResult;
    }
}
