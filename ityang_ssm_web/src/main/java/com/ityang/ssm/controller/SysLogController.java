package com.ityang.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ityang.ssm.domain.SysLog;
import com.ityang.ssm.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @Author: 云萧YYY
 * @DateTime: 2021/5/22
 * @Description: 日志展示
 */
@Controller
@RequestMapping("/sysLog")
public class SysLogController {

    @Autowired
    private SysLogService sysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "size",required = true,defaultValue = "4") Integer size,
                                @RequestParam(name = "page",required = true,defaultValue = "1") Integer page){

        List<SysLog> sysLogs = sysLogService.findAll(size, page);
        PageInfo pageInfo=new PageInfo();
        pageInfo.setList(sysLogs);
        ModelAndView mv=new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("syslog-list");
        return  mv;
    }
}
