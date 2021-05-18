package com.ityang.ssm.service;

import com.ityang.ssm.domain.Permission;

import java.util.List;

public interface PermissionService {

    List<Permission> findAll(int page,int size);

    void add(Permission permission);

    Permission findById(String id);

    void delete(String id);
}
