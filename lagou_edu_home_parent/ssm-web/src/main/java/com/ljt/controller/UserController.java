package com.ljt.controller;


import com.github.pagehelper.PageInfo;
import com.ljt.domain.ResponseResult;
import com.ljt.domain.Role;
import com.ljt.domain.User;
import com.ljt.domain.UserVo;
import com.ljt.service.UserService;
import com.sun.org.apache.bcel.internal.generic.NEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVo userVo) {
        PageInfo pageInfo = userService.findAllUserByPage(userVo);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", pageInfo);
        List<User> list = pageInfo.getList();
        System.out.println(list);
        return responseResult;
    }

    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(@RequestParam int id, @RequestParam
            String status) {
        if ("ENABLE".equalsIgnoreCase(status)) {
            status = "DISABLE";
        } else {
            status = "ENABLE";
        }
        userService.updateUserStatus(id, status);
        ResponseResult responseResult = new ResponseResult(true, 200, "响应成功", status);
        return responseResult;
    }

    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest httpServletRequest) throws Exception {
        User login = userService.login(user);
        ResponseResult result = null;
        if (login != null) {
            String access_token = UUID.randomUUID().toString();
            Map<String, Object> map = new HashMap<>();
            map.put("access_token", access_token);
            map.put("user_id", login.getId());
            map.put("user", login);

            HttpSession session = httpServletRequest.getSession();
            session.setAttribute("user_id", login.getId());
            session.setAttribute("access_token", access_token);
            result = new ResponseResult(true, 1, "响应成功", map);
        } else {
            result = new ResponseResult(true, 1, "用户名密码错误", null);
        }
        return result;
    }

    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(int id) {
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true, 200, "分配角色回显成功", roleList);
    }

    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVo userVo) {
        userService.userContextRole(userVo);
        return new ResponseResult(true, 200, "分配角色成功", null);
    }

    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");

        if (token.equals(access_token)) {
            int user_id = (Integer) session.getAttribute("user_id");
            ResponseResult result = userService.getUserPermissions(user_id);
            return result;

        } else {
            ResponseResult result = new ResponseResult(false, 400, "获取失败", "");
            return result;
        }
    }
}
