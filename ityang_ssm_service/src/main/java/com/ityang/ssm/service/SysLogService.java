package com.ityang.ssm.service;

import com.ityang.ssm.domain.SysLog;

import java.util.List;

/**
 * @Author: 云萧YYY
 * @DateTime: 2021/5/22
 * @Description: TODO
 */

public interface SysLogService {

    public void insertLog(SysLog sysLog) throws Exception;

    List<SysLog> findAll(Integer page, Integer size);
}
