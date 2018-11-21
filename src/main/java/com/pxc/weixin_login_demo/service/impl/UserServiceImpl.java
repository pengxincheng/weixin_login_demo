package com.pxc.weixin_login_demo.service.impl;

import com.pxc.weixin_login_demo.domain.User;
import com.pxc.weixin_login_demo.dto.req.LoginReq;
import com.pxc.weixin_login_demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 15:38
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User login(LoginReq loginReq) {

        return null;
    }
}
