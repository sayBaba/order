package com.hz.order.iClient;

import com.hz.order.iClient.impl.GoodsFeignClentsImpl;
import com.hz.order.vo.Item;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * fegin调用商品
 */
@FeignClient(value = "app-item",fallback = GoodsFeignClentsImpl.class )
public interface GoodsFeignClents {

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/item/{id}", method = RequestMethod.POST)
    public Item queryOrderById(@PathVariable("id") Long id);

    @RequestMapping(value = "/test/{id}", method = RequestMethod.GET)
    public String test(@PathVariable("id") Long id);
}
