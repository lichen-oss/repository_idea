package com.ljt.service;

import com.github.pagehelper.PageInfo;
import com.ljt.domain.*;

import java.util.List;

public interface UserService {

    public  PageInfo findAllUserByPage(UserVo userVo);

    public void updateUserStatus(int id, String status);

    public User login(User user) throws Exception;

    List<Role> findUserRelationRoleById(int id) ;

//    void deleteUserContextRole(Integer userId);

    void userContextRole(UserVo userVo);

    ResponseResult getUserPermissions(Integer id);
}
