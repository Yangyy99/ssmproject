package com.ityang.ssm.service;

import com.ityang.ssm.domain.Product;

import java.util.List;

public interface ProductService {
    List<Product> findAll(int page ,int size) throws Exception;

    void save(Product product) throws Exception;


    void deleteById(String id);
}
