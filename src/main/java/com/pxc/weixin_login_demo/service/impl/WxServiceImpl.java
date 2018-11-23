package com.pxc.weixin_login_demo.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.pxc.weixin_login_demo.config.WxConfig;
import com.pxc.weixin_login_demo.dto.req.WxReq;
import com.pxc.weixin_login_demo.service.WxService;
import org.apache.commons.codec.digest.DigestUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

//import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 17:28
 */
@Service
public class WxServiceImpl implements WxService {

    private static final Logger logger = LoggerFactory.getLogger(WxServiceImpl.class);

    @Override
    public boolean validate(WxReq wxReq) {

        List<String> params = new ArrayList<>();
        params.add(WxConfig.TOKEN);
        params.add(wxReq.getTimestamp());
        params.add(wxReq.getNonce());

        Collections.sort(params, Comparator.naturalOrder());

        String tmpStr = DigestUtils.sha1Hex(params.get(0) + params.get(1) + params.get(2));

        if(wxReq.getSignature().equals(tmpStr)){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getWxAccessToken(String appId, String secret) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String,String> param = new HashMap<>();
            param.put("grant_type","client_credential");
            param.put("appid",appId);
            param.put("secret",secret);

            String resp = restTemplate.getForObject(WxConfig.ACCESS_TOKEN_URL,String.class,param);
            logger.info("获取token接口，微信返回：{}",resp);
            JSONObject jsonObject = JSONObject.parseObject(resp);
             if (jsonObject.containsKey("access_token")){
                 return jsonObject.getString("access_token");
             }
             return null;
        }catch (Exception e){
            logger.error(e.getMessage(),e);
            return null;
        }
    }

    @Override
    public JSONObject getQrCode(String accessToken,int sceneId) {
        RestTemplate restTemplate = new RestTemplate();
        Map<String,Object> param = new HashMap<>();
        //{"expire_seconds": 604800, "action_name": "QR_SCENE", "action_info": {"scene": {"scene_id": 123}}}
        param.put("expire_seconds",604800);
        param.put("action_name","QR_SCENE");
        Map<String,Object> scene = new HashMap<>();
        scene.put("scene_id",sceneId);
        param.put("action_info",scene);

        logger.info("获取二维码参数：{}",JSON.toJSONString(param));
        String resp = restTemplate.postForObject(WxConfig.QR_CODE_TICKET_URL,JSON.toJSONString(param),String.class,accessToken); //请求需要严格要就传jsonString
        logger.info("调用获取二维码接口返回：{}",resp);
        JSONObject result = JSONObject.parseObject(resp);
        if(result.containsKey("ticket")){
            return result;
        }
        return null;
    }
}
