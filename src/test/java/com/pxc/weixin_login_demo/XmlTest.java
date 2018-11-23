package com.pxc.weixin_login_demo;


import com.pxc.weixin_login_demo.dto.wx.MsgType;
import com.pxc.weixin_login_demo.dto.wx.ScanMessage;
import com.pxc.weixin_login_demo.dto.wx.WxBaseMessage;

import com.pxc.weixin_login_demo.utils.XmlUtil;

import org.junit.Test;

import javax.xml.bind.JAXBException;

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
}
