package com.pxc.weixin_login_demo;


import com.alibaba.fastjson.JSON;
import com.pxc.weixin_login_demo.dto.wx.MsgType;
import com.pxc.weixin_login_demo.dto.wx.ScanMessage;
import com.pxc.weixin_login_demo.dto.wx.WxBaseMessage;

import com.pxc.weixin_login_demo.utils.XmlUtil;

import org.junit.Test;

import javax.xml.bind.JAXBException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/23
 * @Time 11:42
 */
public class XmlTest {

    @Test
    public void obj2xml(){
        WxBaseMessage wxBaseMessage = new WxBaseMessage();
        wxBaseMessage.setCreateTime("123");
        wxBaseMessage.setEvent("456");
        wxBaseMessage.setMsgType("789");
        wxBaseMessage.setFromUserName("1");
        wxBaseMessage.setToUserName("2");


    }


    @Test
    public void xml2obj(){
        String xml = "<xml>" +
                "  <ToUserName>2</ToUserName>" +
                "  <FromUserName>1</FromUserName>" +
                "  <CreateTime>123</CreateTime>" +
                "  <MsgType>789</MsgType>" +
                "  <Event>456</Event>" +
                "  <EventKey>00</EventKey>"+
                "</xml>";



       // WxBaseMessage wxBaseMessage = (WxBaseMessage)xstream.fromXML(xml);
        MsgType msgType  = null;
        try {
            xml.replaceAll("\r\n", "").replaceAll("\r", "").replaceAll("\n", "");
              msgType = XmlUtil.xmlToBean(xml,MsgType.class);
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        System.out.println(msgType.getMsgType());
    }

    @Test
    public void testParam(){

        int sceneId = 1234;

        Map<String,Object> param = new HashMap<>();
        //{"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
        param.put("expire_seconds",604800);
        param.put("action_name","QR_SCENE");

        Map<String,Object> scene = new HashMap<>();
        scene.put("scene_id",sceneId);

        Map<String,Object> sceneMap = new HashMap<>();
        sceneMap.put("scene",scene);

        param.put("action_info",sceneMap);

        System.out.println(JSON.toJSONString(param));
    }
}
