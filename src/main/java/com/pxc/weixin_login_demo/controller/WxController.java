package com.pxc.weixin_login_demo.controller;

import com.alibaba.fastjson.JSON;
import com.pxc.weixin_login_demo.dto.req.WxReq;
import com.pxc.weixin_login_demo.service.WxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 14:44
 */
@Controller
@RequestMapping("/wx")
public class WxController {
    private static final Logger logger = LoggerFactory.getLogger(WxController.class);

    @Resource
    private WxService wxService;

    /**
     * 微信回调 get请求 判断是否是来自微信的请求
     *
     * @return
     */
    @GetMapping("/base")
    @ResponseBody
    public String wxAuth(ModelMap modelMap, WxReq wxReq) {
        logger.info("微信请求参数为：{}", JSON.toJSONString(wxReq));
        try {
            if (wxService.validate(wxReq)) {
                return wxReq.getEchostr();
            }
            return null;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
        return null;
    }
}
