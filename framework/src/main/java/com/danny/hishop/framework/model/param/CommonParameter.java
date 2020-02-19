package com.danny.hishop.framework.model.param;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author huyuyang
 * @date 2019/11/28下午5:14
 */
@Data
@Accessors(chain = true)
public class CommonParameter extends BaseParameter {
    private Long id;
    private String comment;
    private Date createTime;
    private Date updateTime;
}
