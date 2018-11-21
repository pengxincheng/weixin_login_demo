package com.pxc.weixin_login_demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pxc.weixin_login_demo.dao")
public class WeixinLoginDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(WeixinLoginDemoApplication.class, args);
    }
}
