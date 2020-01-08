package com.danny.hishop.framework.model.result;


import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.enums.ResultStatusEnumInterface;

import java.io.Serializable;

/**
 * @author Danny
 * @Title: ServiceResult
 * @Description:
 * @Created on 2018-11-29 12:56:31
 */
public class ServiceResult<R> implements Serializable {

    private static final long serialVersionUID = 7567039181931362380L;

    private ResultStatusEnumInterface resultStatusEnum;
    private R data;
    private String message;

    public R getData() {
        return data;
    }

    public ServiceResult setData(R data) {
        this.data = data;
        return this;
    }

    public ServiceResult() {
    }

    public ServiceResult(ResultStatusEnumInterface resultStatusEnum) {
        this.resultStatusEnum = resultStatusEnum;
    }

    public ServiceResult(ResultStatusEnumInterface resultStatusEnum, R data) {
        this.resultStatusEnum = resultStatusEnum;
        this.data = data;
    }

    public ServiceResult(ResultStatusEnumInterface resultStatusEnum, String message) {
        this.resultStatusEnum = resultStatusEnum;
        this.message = message;
    }

    public ServiceResult(ResultStatusEnumInterface resultStatusEnum, String message,R data) {
        this.resultStatusEnum = resultStatusEnum;
        this.message = message;
        this.data=data;
    }

    public ResultStatusEnumInterface getResultStatusEnum() {
        return resultStatusEnum;
    }

    public ServiceResult setResultStatusEnum(ResultStatusEnumInterface resultStatusEnum) {
        this.resultStatusEnum = resultStatusEnum;
        this.message=resultStatusEnum.getDescription();
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ServiceResult<R> setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return resultStatusEnum == ResultStatusEnum.SUCCESS;
    }

    public boolean isFail() {
        return resultStatusEnum != ResultStatusEnum.SUCCESS;
    }
}
