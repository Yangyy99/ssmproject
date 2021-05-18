package com.ityang.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ityang.ssm.dao.OrdersDao;
import com.ityang.ssm.domain.Orders;
import com.ityang.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersDao ordersDao;
    @Override
    public List<Orders> findAll(int page,int size) {
        //pageNum 页码值，pageSize 每页显示条数，必须写在调用分页的代码之前
        PageHelper.startPage(page,size);
        return ordersDao.findAll() ;
    }

    @Override
    public Orders findById(String ordersId) {
        return ordersDao.findById(ordersId);
    }
}
