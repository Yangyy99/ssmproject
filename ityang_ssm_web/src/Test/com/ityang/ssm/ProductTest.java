package com.ityang.ssm;

import com.ityang.ssm.dao.ProductDao;
import com.ityang.ssm.domain.Product;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class ProductTest {
    @Test
    public void productTest() throws Exception {
        ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
        ProductDao productDao = ac.getBean(ProductDao.class);
        List<Product> products = productDao.findAll();
        System.out.println(products.get(0).getCityName());
    }
}
