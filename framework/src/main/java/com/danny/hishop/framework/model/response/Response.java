package com.danny.hishop.framework.model.response;

import com.danny.hishop.framework.model.BaseModel;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/26下午6:38
 */
@Data
@Accessors(chain = true)
public class Response<T> extends BaseModel {
    private int code;
    private String message;
    private T data;

    public static <T> Response<T> build(int code, String message, T data) {
        return new Response<T>().setCode(code).setMessage(message).setData(data);
    }

    public static <T> Response<T> build(int code, String message) {
        return new Response<T>().setCode(code).setMessage(message);
    }

    public static <T> Response<T> build(ServiceResult<T> serviceResult) {
        return new Response<T>()
                .setCode(serviceResult.getResultStatusEnum().getCode())
                .setMessage(serviceResult.getMessage())
                .setData(serviceResult.getData());
    }

    public static <T> Response<T> buildSuccess(T data) {
        return new Response<T>()
                .setCode(ResultStatusEnum.SUCCESS.getCode())
                .setMessage(ResultStatusEnum.SUCCESS.getDescription())
                .setData(data);
    }

    public static <T> Response<T> buildSuccess() {
        return new Response<T>()
                .setCode(ResultStatusEnum.SUCCESS.getCode())
                .setMessage(ResultStatusEnum.SUCCESS.getDescription());
    }

    public boolean isSuccess() {
        return ResultStatusEnum.SUCCESS.getCode() == code;
    }

    public boolean isFail() {
        return ResultStatusEnum.SUCCESS.getCode() != code;
    }
}
