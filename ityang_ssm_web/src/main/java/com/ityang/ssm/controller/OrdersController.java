package com.ityang.ssm.controller;

import com.github.pagehelper.PageInfo;
import com.ityang.ssm.domain.Orders;
import com.ityang.ssm.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 查询全部 没有分页
     * @return
     */
   /* @RequestMapping("/findAll.do")
    public ModelAndView findAll(){

        List<Orders> orders = ordersService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("ordersList",orders);
        mv.setViewName("orders-list");

        return mv;

    }*/

    /***
     * 带分页的查询所有订单
     * @param page 页码
     * @param size 一页多少个元素
     * @return
     */
   @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")int page,
                                @RequestParam(name ="size",required = true,defaultValue = "4")int size){

        ModelAndView mv=new ModelAndView();
        List<Orders> orders = ordersService.findAll(page,size);

        //pageInfo 就是一个分页的bean
        PageInfo pageInfo=new PageInfo(orders);
        System.out.println(pageInfo.getTotal());
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");

        return mv;

    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true) String ordersId){

        ModelAndView mv=new ModelAndView();
        Orders orders = ordersService.findById(ordersId);
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");
        return mv;

    }




}
