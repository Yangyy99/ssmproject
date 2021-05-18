package com.ityang.ssm.service.impl;

import com.ityang.ssm.dao.UserDao;
import com.ityang.ssm.domain.Role;
import com.ityang.ssm.domain.UserInfo;
import com.ityang.ssm.service.IUserService;
import com.ityang.ssm.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userDao.findByUsername(username);
        User user=new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus()==0?false:true,
                true,true,true,getAuthority(userInfo.getRoles()));

        return user;
    }

    public List<GrantedAuthority> getAuthority(List<Role> roles){

        List<GrantedAuthority> authorities=new ArrayList<>();

        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return authorities;
    }

    @Override
    public List<UserInfo> findAll() {
        return userDao.findAll();
    }



    @Override
    public void add(UserInfo userInfo) {
        String id = UuidUtils.getUuid();
        userInfo.setId(id);
        //对密码进行加密
        userInfo.setPassword(passwordEncoder.encode(userInfo.getPassword()));


        userDao.add(userInfo);
    }


    @Override
    public UserInfo findById(String id) {
        return userDao.findById(id);
    }

    @Override
    public List<Role> findRolesByUserId(String id) {
        return userDao.findRolesByUserId(id);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for (String roleId : roleIds) {

            userDao.addRoleToUser(userId,roleId);
        }
    }

    @Override
    public List<Role> findUserByIdAndOtherRole(String id) {
        return userDao.findUserByIdAndOtherRole(id);
    }

    @Override
    public void deleteRoleToUser(String[] roleIds) {
        for (String roleId : roleIds) {

            userDao.deleteRoleToUser(roleId);

        }
    }
}
