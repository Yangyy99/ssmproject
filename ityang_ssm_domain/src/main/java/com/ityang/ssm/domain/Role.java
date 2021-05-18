package com.ityang.ssm.domain;

import java.io.Serializable;
import java.util.List;

public class Role implements Serializable {

    private String id;
    private String roleName;
    private String roleDesc;

    /*
    * 角色与权限是多对多的关系
    * */
    private List<Permission> permissions;

    /*
    * 角色与用户也是多对多的关系
    * */
    private List<UserInfo> users;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }

    public List<UserInfo> getUsers() {
        return users;
    }

    public void setUsers(List<UserInfo> users) {
        this.users = users;
    }
}
