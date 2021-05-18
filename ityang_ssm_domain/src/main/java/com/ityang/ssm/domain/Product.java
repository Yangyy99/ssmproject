package com.ityang.ssm.domain;

import com.ityang.ssm.utils.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;

import java.util.Date;

/**
 * 产品的信息
 */
public class Product implements Serializable {


    //主键
    private String id;
    //产品编号
    private String productNum;

    private String productName;
    private String cityName;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    private Date departureTime;
    private String departureTimeStr; //展示格式化的数据
    private String productDesc;  //商品描述
    private Integer productStatus;
    private String productStatusStr;
    private Double productPrice;

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductNum() {
        return productNum;
    }

    public void setProductNum(String productNum) {
        this.productNum = productNum;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Date getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(Date departureTime) {
        this.departureTime = departureTime;
    }

    public String getDepartureTimeStr() {
        if (departureTime!=null){
            departureTimeStr= DateUtils.date2String(departureTime,"yyyy-MM-dd  HH:mm");
        }
        return departureTimeStr;
    }

    public void setDepartureTimeStr(String departureTimeStr) {
        this.departureTimeStr = departureTimeStr;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Integer getProductStatus() {

        return productStatus;
    }

    public void setProductStatus(Integer productStatus) {
        this.productStatus = productStatus;
    }

    public String getProductStatusStr() {
        if (productStatus!=null){
            if (productStatus==0){
                productStatusStr="关闭";
            }else {
                productStatusStr="开启";
            }
        }
        return productStatusStr;
    }

    public void setProductStatusStr(String productStatusStr) {
        this.productStatusStr = productStatusStr;
    }

}
