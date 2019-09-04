package com.hz.order.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 读取配置
 */
@Data
@Configuration
public class ReadConfig {

    @Value("${myspcloud.item.url}")
    private String itemUrl;

}
