package com.danny.hishop.business.aggregation.model.user.param;


import com.danny.hishop.framework.model.param.BaseParameter;

/**
 * @author Danny
 * @Title: RegisterParameter
 * @Description:
 * @Created on 2018-11-29 13:03:40
 */
public class RegisterParameter extends BaseParameter {
    private String userName;
    private String password;

    public String getUserName() {
        return userName;
    }

    public RegisterParameter setUserName(String userName) {
        this.userName = userName;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterParameter setPassword(String password) {
        this.password = password;
        return this;
    }
}
