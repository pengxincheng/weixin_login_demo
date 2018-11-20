package com.pxc.weixin_login_demo.dto.req;

/**
 * @author pengxincheng@ipaynow.cn
 * @Date: 2018/11/20
 * @Time 15:40
 */
public class LoginReq {

    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
