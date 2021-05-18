package com.ityang.ssm.dao;

import com.ityang.ssm.domain.Member;
import com.ityang.ssm.domain.Orders;
import com.ityang.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdersDao {



    @Select("select * from orders")
    @Results({
            @Result(id = true,property ="id",column = "id"),
            @Result(property = "olderNum",column = "olderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one = @One(select ="com.ityang.ssm.dao.ProductDao.findById"))})
    List<Orders> findAll();

    /**
     *根据订单的id查询出所有的订单信息，包含产品，旅客，会员的信息
     * @param ordersId
     * @return
     */
    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true,property ="id",column = "id"),
            @Result(property = "olderNum",column = "olderNum"),
            @Result(property = "orderTime",column = "orderTime"),
            @Result(property = "peopleCount",column = "peopleCount"),
            @Result(property = "orderStatus",column = "orderStatus"),
            @Result(property = "payType",column = "payType"),
            @Result(property = "orderDesc",column = "orderDesc"),
            @Result(property = "product",column = "productId",javaType = Product.class,
                    one = @One(select ="com.ityang.ssm.dao.ProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,
                    one = @One(select = "com.ityang.ssm.dao.MemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class,
                    many = @Many(select = "com.ityang.ssm.dao.TravellerDao.findByOrdersId")
                 )
    })
    Orders findById(String ordersId);
}
