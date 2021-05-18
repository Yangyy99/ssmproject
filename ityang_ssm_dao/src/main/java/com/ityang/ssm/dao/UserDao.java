package com.ityang.ssm.dao;

import com.ityang.ssm.domain.Role;
import com.ityang.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;


@Repository
public interface UserDao {


    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
            many = @Many(select = "com.ityang.ssm.dao.RoleDao.findByUserId"))
    })
    UserInfo findByUsername(String username);

    //查询所有的用户
    @Select("select * from users")
    List<UserInfo> findAll();

    //添加用户
    @Insert("insert into users values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    void add(UserInfo userInfo);

    //查询用户
    @Select("select * from users where id=#{id}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "username",column = "username"),
            @Result(property = "password",column = "password"),
            @Result(property = "email",column = "email"),
            @Result(property = "phoneNum",column = "phoneNum"),
            @Result(property = "status",column = "status"),
            @Result(property = "roles",column = "id",javaType = java.util.List.class,
                    many = @Many(select = "com.ityang.ssm.dao.RoleDao.findByUserId"))

    })
    UserInfo findById(String id);

    /**
     * 查询与用户没有的角色
     * @param id
     * @return
     */
    @Select("select * from role where id  not in(select roleId from users_role where userId=#{userId})")
    List<Role> findRolesByUserId(@Param("userId")String id);

    /**
     * 查询与用户有的角色
     * @param id
     * @return
     */
    @Select("select * from role where id in(select roleId from users_role where userId=#{userId})")
    List<Role> findUserByIdAndOtherRole(@Param("userId") String id);
    /**
     * 对中间表users_role 进行操作 为用户添加角色
     * @param userId
     * @param roleId
     */
    @Insert("insert into users_role values(#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId,
                       @Param("roleId") String roleId);

    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteRoleToUser(@Param("roleId") String roleId);
}
