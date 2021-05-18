package com.ityang.ssm.service.impl;

import com.github.pagehelper.PageHelper;
import com.ityang.ssm.dao.ProductDao;
import com.ityang.ssm.domain.Product;
import com.ityang.ssm.service.OrdersService;
import com.ityang.ssm.service.ProductService;
import com.ityang.ssm.utils.UuidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;
    @Autowired
    private OrdersService ordersService;

    public List<Product> findAll(int page,int size) throws Exception {
        PageHelper.startPage(page,size);
        return productDao.findAll();
    }

    @Override
    public void save(Product product) throws Exception {
        String  uuid=UuidUtils.getUuid();
        product.setId(uuid);
        productDao.save(product);
    }

    @Override
    public void deleteById(String id) {


        productDao.deleteById(id);

    }
}
