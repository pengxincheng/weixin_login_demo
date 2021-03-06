package com.pxc.weixin_login_demo.config;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 17:42
 */
public interface WxConfig {
    String APP_ID = "wx496ae3c06bd9638c";
    String APP_SECRET = "438bf939ea9ac51713bfab74a0c27909";
    String TOKEN = "pxc";
    String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type={grant_type}&appid={appid}&secret={secret}";
    String QR_CODE_TICKET_URL = "https://api.weixin.qq.com/cgi-bin/qrcode/create?access_token={accessToken}";
    String QR_CODE_URL = "https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";

}
