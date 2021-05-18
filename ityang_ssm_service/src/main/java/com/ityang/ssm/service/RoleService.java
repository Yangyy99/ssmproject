package com.ityang.ssm.service;


import com.ityang.ssm.domain.Permission;
import com.ityang.ssm.domain.Role;

import java.util.List;

public interface RoleService {

    List<Role> findAll();

    void add(Role role);

    Role findById(String id);

    void delete(String id);

    List<Permission> findPermissionByRoleId(String id);


    void addPermissionToRole(String roleId, String[] permissionIds);

    List<Permission> findRoleByIdAndOtherPermission(String id);

    void deletePermissionToRole(String[] permissionIds);
}
