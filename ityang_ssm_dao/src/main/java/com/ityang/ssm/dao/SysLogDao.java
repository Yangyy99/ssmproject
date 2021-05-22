package com.ityang.ssm.dao;

import com.ityang.ssm.domain.SysLog;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: 云萧YYY
 * @DateTime: 2021/5/22
 * @Description: TODO
 */
@Repository
public interface SysLogDao {

    @Insert("insert into syslog values(#{id},#{visitime},#{username},#{ip},#{url},#{excuteTime},#{method})")
    public void insertSysLog(SysLog log) throws Exception;


    @Select("select * from syslog")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="visitTime",property="visitime"),
            @Result(column="ip",property="ip"),
            @Result(column="url",property="url"),
            @Result(column="executionTime",property="excuteTime"),
            @Result(column="method",property="method"),
            @Result(column="username",property="username")
    })
    List<SysLog> findAll();
}
