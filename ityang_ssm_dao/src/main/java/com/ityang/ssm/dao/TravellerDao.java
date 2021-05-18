package com.ityang.ssm.dao;

import com.ityang.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravellerDao {
    /**
     * 根据订单的id查询所有旅客
     * @return
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where order_traveller.orderId=#{ordersId})")
    List<Traveller> findByOrdersId(String ordersId);
}
