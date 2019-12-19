package com.danny.hishop.business.aggregation.feign;

import com.alibaba.fastjson.JSONObject;
import com.danny.hishop.business.aggregation.AggregationApplicationTests;
import com.danny.hishop.business.aggregation.model.user.dto.AddressDTO;
import com.danny.hishop.business.aggregation.feign.user.UserService;
import com.danny.hishop.framework.model.response.Response;
import com.danny.hishop.framework.model.result.ServiceResult;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/25下午2:04
 */
public class UserServiceTest extends AggregationApplicationTests {

    @Autowired
    private UserService userService;

    @Test
    public void getAddressByUserNameTest() {
        Response<List<AddressDTO>> result=userService.getAddressByUserName("82Z76oIu");
        System.out.println(JSONObject.toJSONString(result));
    }

}
