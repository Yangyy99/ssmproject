package com.ityang.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ityang.ssm.dao.SysLogDao;
import com.ityang.ssm.domain.SysLog;
import com.ityang.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @Author: 云萧YYY
 * @DateTime: 2021/5/22
 * @Description: TODO
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogDao sysLogDao;
    @Override
    public void insertLog(SysLog sysLog) throws Exception {
        sysLogDao.insertSysLog(sysLog);
    }

    @Override
    public List<SysLog> findAll(Integer size, Integer page) {

        PageHelper.startPage(page,size);
        List<SysLog> list=sysLogDao.findAll();
        for (SysLog sysLog : list) {
            Date visitime = sysLog.getVisitime();
            DateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String time = df.format(visitime);
            sysLog.setTime(time);

            long excuteTime = sysLog.getExcuteTime();
            sysLog.setExcuteTime(excuteTime/1000);
        }
        return list;
    }
}
