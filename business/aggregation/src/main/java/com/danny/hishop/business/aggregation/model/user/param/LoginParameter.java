package com.danny.hishop.business.aggregation.model.user.param;

import com.danny.hishop.framework.model.param.BaseParameter;

/**
 * @author Danny
 * @Title: LoginParameter
 * @Description:
 * @Created on 2018-11-29 12:54:38
 */
public class LoginParameter extends BaseParameter {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public LoginParameter setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginParameter setPassword(String password) {
        this.password = password;
        return this;
    }
}
