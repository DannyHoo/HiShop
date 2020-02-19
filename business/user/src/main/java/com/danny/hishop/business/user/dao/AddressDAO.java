package com.danny.hishop.business.user.dao;

import com.danny.hishop.business.user.domain.AddressDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * @author huyuyang
 * @date 2019/11/26下午9:37
 */
@Mapper
public interface AddressDAO {
    @Select("select * from t_address where userName=#{userName}")
    List<AddressDO> findByUserName(String userName);

    @Select("select * from t_address where userName=#{userName} and isDefault=#{isDefault}")
    List<AddressDO> findByUserNameAndIsDefault(@Param("userName") String userName, @Param("isDefault") Integer isDefault);

    @Insert("insert into t_address(userName,receiverName,receiverMobileNo,receiverAddress,isDefault) values (#{userName},#{receiverName},#{receiverMobileNo},#{receiverAddress},#{isDefault})")
    @SelectKey(before = false, keyProperty = "id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int saveAddress(AddressDO addressDO);
}
