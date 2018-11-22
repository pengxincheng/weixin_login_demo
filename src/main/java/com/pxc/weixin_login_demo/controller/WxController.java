package com.pxc.weixin_login_demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pxc.weixin_login_demo.config.WxConfig;
import com.pxc.weixin_login_demo.dto.req.WxReq;
import com.pxc.weixin_login_demo.dto.resp.WeCashierResp;
import com.pxc.weixin_login_demo.service.WxService;
import com.pxc.weixin_login_demo.utils.WeCashierRespFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 14:44
 */
@RestController
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

    @PostMapping("/getQrCode")
    public WeCashierResp getQrCode(HttpServletRequest request){
        try {
           /* String accessToken = wxService.getWxAccessToken(WxConfig.APP_ID,WxConfig.APP_SECRET);
            if(null == accessToken){
                return WeCashierRespFactory.builderFail("获取token失败");
            }
            JSONObject result = wxService.getQrCode(accessToken);
            if( null == result) {
                return WeCashierRespFactory.builderFail("获取二维码失败");
            }*/

            WebSocket.sendMessage(String.valueOf(request.getSession().getAttribute("sceneId")),true);
            return WeCashierRespFactory.builderSuccess();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return WeCashierRespFactory.builderEx("获取二维码异常");
        }
    }
}
