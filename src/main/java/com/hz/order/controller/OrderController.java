package com.hz.order.controller;

import com.hz.order.service.IOrderService;
import com.hz.order.vo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class OrderController {

    @Autowired
    private IOrderService iOrderService;


    /**
     * 订单查询入口
     * @param orderId
     * @return
     */
    @ResponseBody
    @GetMapping(value = "order/{orderId}")
    public Order queryOrderById(@PathVariable("orderId") String orderId) {
        Order order = iOrderService.queryOrderById(orderId);
        System.out.println("======iOrderService.queryOrderById(orderId======="+order);
       return order;
    }
}
