package com.danny.hishop.business.aggregation.feign.user;

import com.danny.hishop.business.aggregation.model.user.dto.AddressDTO;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/23下午10:15
 */
@FeignClient(value = "HISHOP-USER", fallback = UserFallback.class,fallbackFactory = UserFallbackFactory.class)
public interface UserService {

    @GetMapping("/user/address/getByUserName/{userName}")
    Response<List<AddressDTO>> getAddressByUserName(@PathVariable String userName);

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
