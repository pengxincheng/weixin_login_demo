package com.pxc.weixin_login_demo.service;

import com.alibaba.fastjson.JSONObject;
import com.pxc.weixin_login_demo.dto.req.WxReq;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 17:19
 */
public interface WxService {

    /**
     * 判断是否是微信的请求
     * @param wxReq
     * @return
     */
    boolean validate(WxReq wxReq);

    /**
     * 获取微信accessToken
     */
    String getWxAccessToken(String appId,String secret);

    /**
     * 获取二维码ticket
     * @param accessToken
     * @return
     */
    JSONObject getQrCode(String accessToken);
}
