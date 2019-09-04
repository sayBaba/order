package com.hz.order.service.impl;

import com.hz.order.config.ReadConfig;
import com.hz.order.iClient.GoodsFeignClents;
import com.hz.order.service.IOrderService;
import com.hz.order.vo.Item;
import com.hz.order.vo.Order;
import com.hz.order.vo.OrderDetail;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.*;

/**
 * 订单服务接口实现类
 */
@Service
public class OrderServiceImpl implements IOrderService {

    private static final Map<String, Order> ORDER_DATA = new HashMap<String, Order>();

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReadConfig readConfig;

    @Autowired
    private GoodsFeignClents goodsFeignClents;

    static {
        // 模拟数据库，构造测试数据
        Order order = new Order();
        order.setOrderId("201810300001");
        order.setCreateDate(new Date());
        order.setUpdateDate(order.getCreateDate());
        order.setUserId(1L);


        ORDER_DATA.put(order.getOrderId(), order);
    }


    @Override
//  @HystrixCommand(fallbackMethod = "queryItemByIdFallbackMethod")
    //容错措施，当这个方法中，调用 getForEntity达到 默认（5秒20次失败） 就会执行getForEntity中的方法
    public Order queryOrderById(String orderId) {

        //根据id查询订单信息
        Order order = ORDER_DATA.get(orderId);
        if(ObjectUtils.isEmpty(order)){
            return null;
        }

        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
//        String url = "http://app-item/item/{id}";
        Long id2 = 2L; //商品ID

        Item item1 = goodsFeignClents.queryOrderById(id2);

        String str = goodsFeignClents.test(id2);

            System.err.println("-----------str = "+str);
//        ResponseEntity<Item> responseEntity1 = restTemplate.getForEntity(url,
//                Item.class, id2);
//        System.out.println("StatusCodeValue = "+responseEntity1.getStatusCodeValue());
//        System.out.println("StatusCode = " + responseEntity1.getStatusCode());
//        Item item1 = responseEntity1.getBody();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setItem(item1);
        orderDetails.add(orderDetail);
        order.setOrderDetails(orderDetails);
        return order;
    }


    /**
     * 请求失败执行的方法
     * fallbackMethod的方法参数个数类型要和原方法一致
     *
     * @param orderId
     * @return
     */
    public Order queryItemByIdFallbackMethod (String orderId){
        //根据id查询订单信息
        Order order = ORDER_DATA.get(orderId);
        if(ObjectUtils.isEmpty(order)){
            return null;
        }
        Long id2 = 2L; //商品ID
        Item item1 = new Item(id2, "查询商品信息出错!", null, null, null);
        List<OrderDetail> orderDetails = new ArrayList<OrderDetail>();
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setItem(item1);
        orderDetails.add(orderDetail);
        order.setOrderDetails(orderDetails);
        return order;
    }



}
