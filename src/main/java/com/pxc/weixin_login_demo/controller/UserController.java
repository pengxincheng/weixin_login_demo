package com.pxc.weixin_login_demo.controller;


import com.pxc.weixin_login_demo.dto.resp.WeCashierResp;
import com.pxc.weixin_login_demo.utils.WeCashierRespFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 14:44
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @RequestMapping("/login")
    public WeCashierResp login(){
        return WeCashierRespFactory.builderSuccess();
    }
}
