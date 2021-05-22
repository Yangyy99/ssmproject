package com.ityang.ssm.domain;


import java.util.Date;

/**
 * @Author: 云萧YYY
 * @DateTime: 2021/5/22
 * @Description: 做aop日志，存放日志的实体类
 */

public class SysLog {

    private String id;
    private Date visitime;  //操作的时间
    private String username;
    private String ip;
    private String url;
    private String method ; // 操作方法
    private long excuteTime ;// 执行时长
    private String time ; // 格式化时间

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getVisitime() {
        return visitime;
    }

    public void setVisitime(Date visitime) {
        this.visitime = visitime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public long getExcuteTime() {
        return excuteTime;
    }

    public void setExcuteTime(long excuteTime) {
        this.excuteTime = excuteTime;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
}
