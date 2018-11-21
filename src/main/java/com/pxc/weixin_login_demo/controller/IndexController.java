package com.pxc.weixin_login_demo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/21
 * @Time 14:49
 */
@Controller
public class IndexController {

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping("/index")
    public String index(){
        logger.info("进入登录页。。。。");
        return "index.ftl";
    }
}
