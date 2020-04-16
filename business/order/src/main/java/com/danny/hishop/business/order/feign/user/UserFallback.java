package com.danny.hishop.business.order.feign.user;

import com.danny.hishop.business.order.feign.user.dto.AddressDTO;
import com.danny.hishop.business.order.feign.user.dto.UserDTO;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuyang
 * @date 2019/11/29下午3:48
 */
@Slf4j
@Component
public class UserFallback implements UserService {

    @Override
    public Response<List<AddressDTO>> getAddressByUserName(String userName) {
        log.info("UserFallback.getAddressByUserName {} params:{}", ResultStatusEnum.SERVICE_FUSE_OPEN.getCode(),userName);
        return Response.build(ResultStatusEnum.SERVICE_FUSE_OPEN.getCode(), "hystrix:", new ArrayList<>());
    }

    @Override
    public Response<UserDTO> getUserByUserName(String userName) {
        log.info("UserFallback.getUserByUserName {} params:{}", ResultStatusEnum.SERVICE_FUSE_OPEN.getCode(),userName);
        return Response.build(ResultStatusEnum.SERVICE_FUSE_OPEN.getCode(), "hystrix:", new UserDTO());
    }
}
