package com.pxc.weixin_login_demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.pxc.weixin_login_demo.config.WxConfig;
import com.pxc.weixin_login_demo.dao.UserMapper;
import com.pxc.weixin_login_demo.domain.User;
import com.pxc.weixin_login_demo.dto.req.LoginReq;
import com.pxc.weixin_login_demo.dto.resp.WeCashierResp;
import com.pxc.weixin_login_demo.service.WxService;
import com.pxc.weixin_login_demo.utils.WeCashierRespFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 14:44
 */
@RestController
@RequestMapping("/user")
public class UserController {
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Resource
    private UserMapper userMapper;
    @Resource
    private WxService wxService;

    @RequestMapping("/login")
    public WeCashierResp login(LoginReq loginReq, HttpServletRequest request){
        User user = userMapper.selectByPrimaryKey(loginReq.getUserName());
        if(null != user){

              String accessToken = wxService.getWxAccessToken(WxConfig.APP_ID,WxConfig.APP_SECRET);
            if(null == accessToken){
                return WeCashierRespFactory.builderFail("获取token失败");
            }
            JSONObject result = wxService.getQrCode(accessToken);
            if( null == result) {
                return WeCashierRespFactory.builderFail("获取二维码失败");
            }

            Random random = new Random();
            int sceneId = random.nextInt(10000);
            request.getSession().setAttribute("user",user);
            request.getSession().setAttribute("sceneId",sceneId);

            Map<String,Object> resultData = new HashMap<>();
            resultData.put("sceneId",sceneId);
            resultData.put("qrcode",result);
            return WeCashierRespFactory.builderSuccess(resultData);
        }else{
            return WeCashierRespFactory.builderFail("用户名或密码错误！");
        }
    }
}
