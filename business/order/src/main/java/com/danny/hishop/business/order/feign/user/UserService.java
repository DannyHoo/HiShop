package com.danny.hishop.business.order.feign.user;

import com.danny.hishop.business.order.feign.user.dto.AddressDTO;
import com.danny.hishop.business.order.feign.user.dto.UserDTO;
import com.danny.hishop.framework.model.response.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * 熔断优先级：fallback>fallbackFactory
 *
 * @author huyuyang
 * @date 2019/11/23下午10:15
 */
@Component
@FeignClient(value = "hishop-user", /*fallback = UserFallback.class,*/fallbackFactory = UserFallbackFactory.class)
public interface UserService {

    @GetMapping("/user/address/getByUserName/{userName}")
    Response<List<AddressDTO>> getAddressByUserName(@PathVariable String userName);

    @GetMapping("/user/user/get/{userName}")
    Response<UserDTO> getUserByUserName(@PathVariable String userName);

    /*@PostMapping("/open/encrypt/getRSA")
    JSONObject getRSA();

    @PostMapping("/open/encrypt/getKey")
    @Headers({"ContentType:application/json"})
    JSONObject getKey(Object jsonObject);

    @PostMapping("/api/users/lookupCurrentUser")
    @Headers({"ContentType:application/json"})
    JSONObject getOpayUserInfo(@RequestHeader("Authorization") String token);

    @PostMapping("/api/users/queryBvnInfo")
    @Headers({"ContentType:application/json"})
    JSONObject queryBvnInfo(String json);*/
}
