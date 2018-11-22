package com.pxc.weixin_login_demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/22
 * @Time 18:38
 * 必须配置这个bean 否则客户端连接报404
 */
@Configuration
public class WebSocketConfig {

    @Bean
    public ServerEndpointExporter serverEndpointExporter() {

        return new ServerEndpointExporter();
    }

}
