package com.ityang.ssm.service.impl;

import com.ityang.ssm.dao.RoleDao;
import com.ityang.ssm.dao.UserDao;
import com.ityang.ssm.domain.Permission;
import com.ityang.ssm.domain.Role;
import com.ityang.ssm.service.RoleService;
import com.ityang.ssm.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;



    @Override
    public List<Role> findAll() {
        return roleDao.findAll();
    }

    @Override
    public void add(Role role) {
        String id = UuidUtils.getUuid();
        role.setId(id);
        roleDao.add(role);
    }

    @Override
    public Role findById(String id) {
        return roleDao.findById(id);
    }

    @Override
    public void delete(String id) {


        roleDao.deleteFromUser(id);

        roleDao.deleteFromPermission(id);

        roleDao.delete(id);
    }

    @Override
    public List<Permission> findPermissionByRoleId(String id) {
        return roleDao.findPermissionByRoleId(id);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

    @Override
    public List<Permission> findRoleByIdAndOtherPermission(String id) {
        return roleDao.findRoleByIdAndOtherPermission(id);
    }

    @Override
    public void deletePermissionToRole(String[] permissionIds) {
        for (String permissionId : permissionIds) {
            roleDao.deltePermissionToRole(permissionId);
        }
    }
}
