package com.ityang.ssm.dao;

import com.ityang.ssm.domain.Product;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductDao  {

    @Select("select * from product")
    List<Product> findAll() throws Exception;

    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{id},#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product);

    /**
     *根据id查询产品
     */
    @Select("select *from product where id= #{id}")
    Product findById(String id) throws Exception;

    @Delete("delete from product where id=#{id}")
    void deleteById(String id);
}
