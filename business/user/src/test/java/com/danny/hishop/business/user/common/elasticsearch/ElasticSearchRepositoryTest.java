package com.danny.hishop.business.user.common.elasticsearch;

import com.alibaba.fastjson.JSON;
import com.danny.hishop.business.user.UserApplicationTests;
import com.danny.hishop.business.user.dao.UserEsRepository;
import com.danny.hishop.business.user.model.dto.UserDTO;
import com.danny.hishop.framework.util.RandomValueUtil;
import com.danny.hishop.framework.util.StringUtil;
import com.danny.hishop.framework.util.snowflake.autoconfigure.core.Snowflake;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/2/18下午5:06
 */
public class ElasticSearchRepositoryTest extends UserApplicationTests {

    @Autowired
    private Snowflake snowflake;

    @Autowired
    private UserEsRepository userEsRepository;

    /**
     * 插入数据
     * 根据ID查询数据，返回实体
     */
    @Test
    public void saveTest() {
        UserDTO userInsertData = getUser();
        UserDTO userInsertResult = userEsRepository.save(userInsertData);
        Optional<UserDTO> userQueryResult = Optional.empty();
        if (userEsRepository.existsById(userInsertData.getId())) {
            userQueryResult = userEsRepository.findById(userInsertData.getId());
            if (userQueryResult.isPresent()) {
                printResult("结果为：", userQueryResult.get());
            }
        }
        Assert.assertNotNull(userQueryResult.get());
    }

    /**
     * 根据字段查询数据，返回列表
     */
    @Test
    public void queryTest() {
        String userName = "DannyHoo";
        String mobileNo = "13579246810";
        String idCardNo = "130900199004091109";
        String realName = "胡丹尼";
        String comment = "ElasticSearch测试";
        List<UserDTO> userList1 = userEsRepository.findByUserName(userName);
        List<UserDTO> userList2 = userEsRepository.findByMobileNo(mobileNo);
        List<UserDTO> userList3 = userEsRepository.findByIdCardNo(idCardNo);
        List<UserDTO> userList4 = userEsRepository.findByRealName(realName);
        List<UserDTO> userList5 = userEsRepository.findByComment(comment);
        printResult(userList1);
        printResult(userList2);
        printResult(userList3);
        printResult(userList4);
        printResult(userList5);
    }


    private UserDTO getUser() {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(StringUtil.getStringRandom(32))
                .setPassword(StringUtil.getRandomNum(6))
                .setRealName(RandomValueUtil.getEmail(6, 10))
                .setMobileNo("1" + StringUtil.getRandomNum(10))
                .setIdCardNo(StringUtil.getRandomNum(6) + "19900409" + StringUtil.getRandomNum(4))
                .setEmail(RandomValueUtil.getEmail(6, 10))
                .setSalt(StringUtil.getStringRandom(32))
                .setId(snowflake.genId())
                .setComment("ElasticSearch测试")
                .setCreateTime(new Date())
                .setUpdateTime(new Date());
        return userDTO;
    }
}
