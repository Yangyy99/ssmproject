package com.ityang.ssm.dao;

import com.ityang.ssm.domain.Permission;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao {

    @Select("select * from permission  where id in (select permissionId from role_permission where roleId=#{roleId}) ")
    List<Permission> findByRoleId(String roleId);

    @Select("select *from permission")
    List<Permission> findAll();

    @Insert("insert into permission values(#{id},#{permissionName},#{url})")
    void add(Permission permission);






    @Select("select * from permission where id=#{id}")
    @Results({
            @Result(id = true,property ="id",column = "id"),
            @Result(property = "permissionName",column = "permissionName"),
            @Result(property = "url",column = "url"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.ityang.ssm.dao.RoleDao.findAll"))
    })
    Permission findById(String id);



    @Delete("delete from role_permission where permissionId =#{permissionId}")
    void deleteFromRoleAndPermission(@Param("permissionId") String id);

    @Delete("delete from permission where id=#{id}")
    void delete(String id);
}
