package com.danny.hishop.business.user.common.elasticsearch;

import com.danny.hishop.business.user.UserApplicationTests;
import com.danny.hishop.business.user.elasticsearch.AddressDocument;
import com.danny.hishop.business.user.elasticsearch.AddressEsRepository;
import com.danny.hishop.business.user.elasticsearch.UserEsRepository;
import com.danny.hishop.business.user.elasticsearch.UserDocument;
import com.danny.hishop.framework.util.RandomValueUtil;
import com.danny.hishop.framework.util.StringUtil;
import com.danny.hishop.framework.util.snowflake.autoconfigure.core.Snowflake;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public class ElasticSearchRepositoryTest extends UserApplicationTests {

    @Autowired
    private Snowflake snowflake;

    @Autowired
    private UserEsRepository userEsRepository;

    @Autowired
    private AddressEsRepository addressEsRepository;

    /**
     * 插入数据
     * 根据ID查询数据，返回实体
     */
    @Test
    public void saveUserTest() {
        UserDocument userInsertData = getUser();
        UserDocument userInsertResult = userEsRepository.save(userInsertData);
        Optional<UserDocument> userQueryResult = Optional.empty();
        if (userEsRepository.existsById(userInsertData.getId())) {
            userQueryResult = userEsRepository.findById(userInsertData.getId());
            if (userQueryResult.isPresent()) {
                printResult("结果为：", userQueryResult.get());
            }
        }
        Assert.assertNotNull(userQueryResult.get());
    }

    @Test
    public void saveAddressTest() {
        AddressDocument addressInsertData = getAddress();
        AddressDocument addressInsertResult = addressEsRepository.save(addressInsertData);
        Optional<AddressDocument> addressQueryResult = Optional.empty();
        if (addressEsRepository.existsById(addressInsertData.getId())) {
            addressQueryResult = addressEsRepository.findById(addressInsertData.getId());
            if (addressQueryResult.isPresent()) {
                printResult("结果为：", addressQueryResult.get());
            }
        }
        Assert.assertNotNull(addressQueryResult.get());
    }

    private AddressDocument getAddress() {
        AddressDocument addressDocument = new AddressDocument();
        addressDocument.setUserName(StringUtil.getStringRandom(8))
                .setReceiverName(RandomValueUtil.getAddress().get("name").toString())
                .setReceiverMobileNo("1" + StringUtil.getRandomNum(10))
                .setReceiverAddress(RandomValueUtil.getAddress().get("road").toString())
                .setIsDefault(1)
                .setId(snowflake.genId())
                .setComment("ElasticSearch测试")
                .setCreateTime(new Date())
                .setUpdateTime(new Date());
        return addressDocument;
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
        List<UserDocument> userList1 = userEsRepository.findByUserName(userName);
        List<UserDocument> userList2 = userEsRepository.findByMobileNo(mobileNo);
        List<UserDocument> userList3 = userEsRepository.findByIdCardNo(idCardNo);
        List<UserDocument> userList4 = userEsRepository.findByRealName(realName);
        List<UserDocument> userList5 = userEsRepository.findByComment(comment);
        printResult(userList1);
        printResult(userList2);
        printResult(userList3);
        printResult(userList4);
        printResult(userList5);
    }


    private UserDocument getUser() {
        UserDocument userDTO = new UserDocument();
        userDTO.setUserName(StringUtil.getStringRandom(12))
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
