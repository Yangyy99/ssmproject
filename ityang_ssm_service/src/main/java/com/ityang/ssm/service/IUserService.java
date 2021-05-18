package com.ityang.ssm.service;

import com.ityang.ssm.domain.Role;
import com.ityang.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;


public interface IUserService extends UserDetailsService {
    //查询所有的用户
    List<UserInfo> findAll();

    //保存用户
    void add(UserInfo userInfo);

    //查询用户
    UserInfo findById(String id);

    //查找用户可以添加的角色
    List<Role> findRolesByUserId(String id);

    void addRoleToUser(String userId, String[] roleIds);

    //查询用户现在被赋予的角色
    List<Role> findUserByIdAndOtherRole(String id);
    //删除用户角色信息
    void deleteRoleToUser(String[] roleIds);
}
