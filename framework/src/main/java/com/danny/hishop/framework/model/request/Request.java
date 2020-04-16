package com.danny.hishop.framework.model.request;

import com.danny.hishop.framework.model.BaseModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author huyuyang
 * @date 2019/11/26下午6:38
 */
@Data
@Accessors(chain = true)
public class Request<D> extends BaseModel {

    private String requestId;

    private D data;

}
