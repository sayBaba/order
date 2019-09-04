package com.hz.order.service;

import com.hz.order.vo.Order;

/**
 * 订单服务
 */
public interface IOrderService {

    /**
     * 根据订单id查询订单数据
     *
     * @param orderId
     * @return
     */
    public Order queryOrderById(String orderId);
}
