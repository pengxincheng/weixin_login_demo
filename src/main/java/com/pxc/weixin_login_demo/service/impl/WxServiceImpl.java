package com.pxc.weixin_login_demo.service.impl;

import com.pxc.weixin_login_demo.config.WxConfig;
import com.pxc.weixin_login_demo.dto.req.WxReq;
import com.pxc.weixin_login_demo.service.WxService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//import org.apache.commons.codec.digest.DigestUtils;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 17:28
 */
@Service
public class WxServiceImpl implements WxService {

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
        RestTemplate restTemplate = new RestTemplate();
        //restTemplate.getForObject()
        return null;
    }
}
