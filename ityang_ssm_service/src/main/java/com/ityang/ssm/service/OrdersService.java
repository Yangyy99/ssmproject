package com.ityang.ssm.service;

import com.ityang.ssm.domain.Orders;

import java.util.List;

public interface OrdersService {


    List<Orders> findAll(int page,int size);

    Orders findById(String ordersId);
}
