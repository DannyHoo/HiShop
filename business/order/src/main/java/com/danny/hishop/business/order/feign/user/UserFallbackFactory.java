package com.danny.hishop.business.order.feign.user;

import com.danny.hishop.business.order.feign.user.dto.AddressDTO;
import com.danny.hishop.business.order.feign.user.dto.UserDTO;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.response.Response;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuyang
 * @date 2019/11/29下午3:12
 */
@Slf4j
@Component
public class UserFallbackFactory implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable throwable) {
        return new UserService() {
            @Override
            public Response<List<AddressDTO>> getAddressByUserName(String userName) {
                throwable.printStackTrace();
                log.info("UserFallbackFactory.getAddressByUserName fallback; reason was:" + throwable.getMessage());
                return Response.build(ResultStatusEnum.SERVICE_FUSE_OPEN.getCode(), "hystrix:", new ArrayList<>());
            }

            @Override
            public Response<UserDTO> getUserByUserName(String userName) {
                throwable.printStackTrace();
                log.info("UserFallbackFactory.getUserByUserNamefallback; reason was:" + throwable.getMessage());
                return Response.build(ResultStatusEnum.SERVICE_FUSE_OPEN.getCode(), "hystrix:", new UserDTO());
            }
        };

    }
}
