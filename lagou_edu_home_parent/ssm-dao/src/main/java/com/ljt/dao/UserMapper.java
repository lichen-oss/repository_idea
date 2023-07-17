package com.ljt.dao;

import com.ljt.domain.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {

    public List<User> findAllUserByPage(UserVo userVo);

    public void updateUserStatus(@Param("id") int id, @Param("status") String status);

    public User login(User user);

    public List<Role> findUserRelationRoleById(int id);

    void deleteUserContextRole(Integer userId);

    void userContextRole(User_Role_relation user_role_relation);

    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    List<Menu> findSubMenuByPid(Integer pid);

    List<Resource> findResourceByRoleId(List<Integer> ids);
}
