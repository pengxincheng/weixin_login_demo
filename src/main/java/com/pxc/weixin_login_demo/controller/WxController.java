package com.pxc.weixin_login_demo.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.pxc.weixin_login_demo.config.WxConfig;
import com.pxc.weixin_login_demo.dao.UserMapper;
import com.pxc.weixin_login_demo.domain.User;
import com.pxc.weixin_login_demo.domain.UserExample;
import com.pxc.weixin_login_demo.dto.req.WxReq;
import com.pxc.weixin_login_demo.dto.resp.WeCashierResp;
import com.pxc.weixin_login_demo.dto.wx.MsgType;
import com.pxc.weixin_login_demo.dto.wx.ScanMessage;
import com.pxc.weixin_login_demo.dto.wx.WxBaseMessage;
import com.pxc.weixin_login_demo.service.WxService;
import com.pxc.weixin_login_demo.utils.WeCashierRespFactory;
import com.pxc.weixin_login_demo.utils.XmlUtil;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.messaging.simp.user.UserRegistryMessageHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.nio.charset.Charset;

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
    @Resource
    private UserMapper userMapper;

    /**
     * 微信回调 get请求 判断是否是来自微信的请求
     *
     * @return
     */
    @GetMapping("/base")
    public String wxAuth(WxReq wxReq) {
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

    @PostMapping("/base")
    public String message(HttpServletRequest request,WxReq wxReq) {
        logger.info("微信请求参数为：{}", JSON.toJSONString(wxReq));
        try {
            if (wxService.validate(wxReq)) {
                String wxMessage = IOUtils.toString(request.getInputStream(),Charset.defaultCharset());
                logger.info("微信消息："+wxMessage);
                MsgType msgType = XmlUtil.xmlToBean(wxMessage,MsgType.class);
                logger.info("msgType={}",JSON.toJSONString(msgType));
                if("event".equals(msgType.getMsgType())) {
                    if ("subscribe".equals(msgType.getEvent())) {
                        ScanMessage scanMessage = XmlUtil.xmlToBean(wxMessage, ScanMessage.class);
                        logger.info("scanMessage={}", JSON.toJSONString(scanMessage));
                        logger.info("用户openId={}关注公众号", scanMessage.getFromUserName());

                        int sceneId = Integer.parseInt(scanMessage.getEventKey().substring("qrscene_".length()));
                        logger.info("sceneId = {}", sceneId);

                        UserExample userExample = new UserExample();
                        userExample.createCriteria().andSceneIdEqualTo(sceneId);
                        User user = new User();
                        user.setOpenId(scanMessage.getFromUserName());

                        userMapper.updateByExampleSelective(user, userExample);

                        WebSocket.sendMessage(String.valueOf(sceneId), true);
                        logger.info("已向webSocket发送通知");
                    } else if ("unsubscribe".equals(msgType.getEvent())) {
                        WxBaseMessage wxBaseMessage = XmlUtil.xmlToBean(wxMessage, WxBaseMessage.class);
                        logger.info("用户openId={}关注公众号", wxBaseMessage.getFromUserName());
                    } else if ("SCAN".equals(msgType.getEvent())) {
                        ScanMessage scanMessage = XmlUtil.xmlToBean(wxMessage, ScanMessage.class);
                        logger.info("scanMessage={}", JSON.toJSONString(scanMessage));
                        logger.info("用户openId={}扫码登录", scanMessage.getFromUserName());

                        int sceneId = Integer.parseInt(scanMessage.getEventKey());

                        UserExample userExample = new UserExample();
                        userExample.createCriteria().andSceneIdEqualTo(sceneId);

                        User user = userMapper.selectByExample(userExample).get(0);
                        boolean re = user.getOpenId().equals(scanMessage.getFromUserName());

                        WebSocket.sendMessage(String.valueOf(user.getSceneId()), re);
                        logger.info("已向webSocket发送通知");
                    }
                }

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

            WebSocket.sendMessage(String.valueOf(1234),true);
            return WeCashierRespFactory.builderSuccess();
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return WeCashierRespFactory.builderEx("获取二维码异常");
        }
    }
}
