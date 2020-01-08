package com.danny.hishop.gateway.service.impl;

import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.gateway.service.DemoService;
import org.springframework.stereotype.Service;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/1/7下午9:20
 */
@Service
public class DemoServiceImpl implements DemoService {

    @Override
    public ServiceResult asyncRequest() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ServiceResult().setResultStatusEnum(ResultStatusEnum.SUCCESS);
    }
}
