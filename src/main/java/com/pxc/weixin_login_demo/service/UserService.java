package com.pxc.weixin_login_demo.service;

import com.pxc.weixin_login_demo.domain.User;
import com.pxc.weixin_login_demo.dto.req.LoginReq;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 15:37
 */
public interface UserService {

    User login(LoginReq loginReq);
}
