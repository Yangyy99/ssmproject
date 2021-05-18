package com.ityang.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ityang.ssm.dao.PermissionDao;
import com.ityang.ssm.domain.Permission;
import com.ityang.ssm.service.PermissionService;
import com.ityang.ssm.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public List<Permission> findAll(int page,int size) {
        PageHelper.startPage(page,size);
        return permissionDao.findAll();
    }

    @Override
    public void add(Permission permission) {
        String uuid = UuidUtils.getUuid();
        permission.setId(uuid);
        permissionDao.add(permission);

    }

    @Override
    public Permission findById(String id) {
        return permissionDao.findById(id);
    }

    @Override
    public void delete(String id) {

        permissionDao.deleteFromRoleAndPermission(id);
        permissionDao.delete(id);

    }
}
