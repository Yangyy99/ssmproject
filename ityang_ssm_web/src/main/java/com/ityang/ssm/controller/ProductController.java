package com.ityang.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ityang.ssm.domain.Product;
import com.ityang.ssm.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * 查询全部产品
     * @return
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1") int page ,
                                @RequestParam(name = "size",required = true,defaultValue = "5") int size){

        ModelAndView mv=new ModelAndView();
        List<Product> products = null;
        try {
            products = productService.findAll(page,size);
        } catch (Exception e) {
            e.printStackTrace();
        }
        PageInfo pageInfo=new PageInfo(products);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("product-list1");
        return  mv;
    }

    /**
     * 保存产品
     * @param product
     */
    @RequestMapping("/save.do")
    public String save(Product product){

        try {
            productService.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "redirect:findAll.do";

    }

    @RequestMapping("/deleteProduct.do")
    public String deleteProduct(@RequestParam(name ="id")String id){
        productService.deleteById(id);

        return "redirect:findAll.do";
    }




}
