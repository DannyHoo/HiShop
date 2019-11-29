package com.danny.hishop.business.user.dao;

import com.danny.hishop.business.user.domain.UserDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/26下午9:37
 */
@Mapper
public interface UserDAO {

    @Select("select * from t_user")
    List<UserDO> findAll();

    @Select("select * from t_user where userName=#{userName}")
    UserDO findByUserName(@Param("userName") String userName);

    @Insert("insert into t_user(userName,mobileNo,salt,password,email,realName,idCardNo) values (#{userName},#{mobileNo},#{salt},#{password},#{email},#{realName},#{idCardNo})")
    @SelectKey(before = false, keyProperty = "id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int insertUserDO(UserDO userDO);

    @Insert({
            "<script>"
                    + "INSERT INTO t_user (userName,mobileNo,salt,password,email,realName,idCardNo) "
                    + "VALUES "
                    + "<foreach item='com.happycommunity.user' index='index' collection='userDOList' separator=','>"
                    + "(#{com.happycommunity.user.userName},#{com.happycommunity.user.mobileNo},#{com.happycommunity.user.salt},#{com.happycommunity.user.password},#{com.happycommunity.user.email},#{com.happycommunity.user.realName},#{com.happycommunity.user.idCardNo})"
                    + "</foreach>"
                    + "</script>"
    })
    @SelectKey(before = false, keyProperty = "id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int insertUserDOBatch(@Param("userDOList") List<UserDO> userDOList);

}
