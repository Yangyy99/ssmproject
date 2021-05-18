package com.ityang.ssm.dao;

import com.ityang.ssm.domain.Permission;
import com.ityang.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleDao {

    /**
     * 根据用户id查询所有的角色
     * @param userId
     * @return
     */
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
            many = @Many(select = "com.ityang.ssm.dao.PermissionDao.findByRoleId"))

    })
    List<Role> findByUserId(String userId);


    /**
     * 查询全部角色信息
     * @return
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 给谁添加角色
     * @param role
     */
    @Insert("insert into role values(#{id},#{roleName},#{roleDesc})")
    void add(Role role);

    @Select("select *from role where id=#{id}")
    @Results(value = {
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.ityang.ssm.dao.PermissionDao.findByRoleId"))

    })
    Role findById(String id);

    /**
     * 查询出该角色所有没有关联的权限集合
     * @param id
     * @return
     */
    @Select("Select * from permission where id not in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findPermissionByRoleId(@Param("roleId") String id);

    @Select("Select * from permission where id in(select permissionId from role_permission where roleId=#{roleId})")
    List<Permission> findRoleByIdAndOtherPermission(@Param("roleId") String id);

    /**
     * 向role_permission这张表添加纪录 来关联角色与权限之间的关系
     * @param roleId
     * @param permissionId
     */
    @Insert("insert into role_permission values(#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId")String roleId,
                             @Param("permissionId")String permissionId);


    @Delete("delete from role  where id=#{id}")
    void delete(@Param("id") String id);

    @Delete("delete from users_role where roleId=#{roleId} ")
    void deleteFromUser(@Param("roleId")String id);

    @Delete("delete from role_permission where roleId = #{roleId}")
    void deleteFromPermission(@Param("roleId") String id);

    /**
     * 在role_permission表中删除 permission
     * @param permissionId
     */
    @Delete("delete from role_permission where permissionId = #{permission}")
    void deltePermissionToRole(String permissionId);
}
