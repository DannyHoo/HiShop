package com.danny.hishop.business.aggregation.feign.user;

import com.danny.hishop.business.aggregation.model.user.dto.AddressDTO;
import com.danny.hishop.business.aggregation.model.user.dto.UserDTO;
import com.danny.hishop.framework.model.enums.ResultStatusEnum;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
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
                log.info("UserFallbackFactory.getAddressByUserName fallback; reason was:" + throwable.getMessage());
                return Response.build(ResultStatusEnum.SERVICE_FUSE_OPEN.getCode(), "hystrix:", new ArrayList<>());
            }

            @Override
            public Response<UserDTO> getUserByUserName(String userName) {
                log.info("UserFallbackFactory.getUserByUserNamefallback; reason was:" + throwable.getMessage());
                return Response.build(ResultStatusEnum.SERVICE_FUSE_OPEN.getCode(), "hystrix:", new UserDTO());
            }
        };

    }
}
