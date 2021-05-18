package com.ityang.ssm.domain;

import java.io.Serializable;

/**
 * 旅客,游客
 */
public class Traveller implements Serializable {

    private String id;
    private String name;
    private String sex;
    private String phoneNum; //电话
    private Integer credentialsType;  //证件类型 0 身份证 1 护照 2 军官证
    private String credentialsTypeStr;
    private Long credentialsNum;

    private Integer travellerType;
    private String  travellerTypeStr; //游客类型 0成人 ，1儿童

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getCredentialsType() {
        return credentialsType;
    }

    public void setCredentialsType(Integer credentialsType) {
        this.credentialsType = credentialsType;
    }

    public String getCredentialsTypeStr() {
        if (credentialsType!=null){
            if (credentialsType==0){
                credentialsTypeStr="身份证";
            }else if (credentialsType==1){
                credentialsTypeStr="护照";
            }else if (credentialsType==2){
                credentialsTypeStr="军官证";
            }
        }
        return credentialsTypeStr;
    }

    public void setCredentialsTypeStr(String credentialsTypeStr) {
        this.credentialsTypeStr = credentialsTypeStr;
    }

    public Long getCredentialsNum() {
        return credentialsNum;
    }

    public void setCredentialsNum(Long credentialsNum) {
        this.credentialsNum = credentialsNum;
    }

    public Integer getTravellerType() {
        return travellerType;
    }

    public void setTravellerType(Integer travellerType) {
        this.travellerType = travellerType;
    }

    public String getTravellerTypeStr() {
        if (travellerType!=null){
            if (travellerType==0){
                credentialsTypeStr="成人";
            }else if (travellerType==1){
                credentialsTypeStr="儿童";
            }
        }
        return travellerTypeStr;
    }

    public void setTravellerTypeStr(String travellerTypeStr) {
        this.travellerTypeStr = travellerTypeStr;
    }
}
