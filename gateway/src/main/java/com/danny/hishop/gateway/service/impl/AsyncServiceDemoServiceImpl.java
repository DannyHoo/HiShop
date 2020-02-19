package com.danny.hishop.gateway.service.impl;

import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.result.ServiceResult;
import com.danny.hishop.gateway.service.AsyncServiceDemoService;
import org.springframework.stereotype.Service;

/**
 * @author huyuyang
 * @date 2020/1/7下午9:20
 */
@Service
public class AsyncServiceDemoServiceImpl implements AsyncServiceDemoService {

    @Override
    public ServiceResult asyncRequest() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new ServiceResult().setResultStatusEnum(ResultStatusEnum.SUCCESS);
    }
}
