package com.hz.order.iClient.impl;

import com.hz.order.iClient.GoodsFeignClents;
import com.hz.order.vo.Item;
import org.springframework.stereotype.Component;

/**
 * 容错回调类
 */
@Component
public class GoodsFeignClentsImpl implements GoodsFeignClents {

    @Override
    public Item queryOrderById(Long id) {
        Item item1 = new Item(id, "查询商品信息出错!", null, null, null);
        return item1;
    }

    @Override
    public String test(Long id) {
        return "err";
    }
}
