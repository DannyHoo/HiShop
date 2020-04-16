package com.danny.hishop.business.goods.dao;

import com.danny.hishop.business.goods.domain.GoodsDO;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.StatementType;

/**
 * @author Danny
 * @Title: GoodsDAO
 * @Description:
 * @Created on 2019-01-09 22:34:00
 */
@Mapper
public interface GoodsDAO {

    @Select("select * from t_goods where goodsNo=#{goodsNo}")
    //@Select("select * from t_goods where goodsNo=#{goodsNo} for update")
    GoodsDO findByGoodsNo(@Param("goodsNo") String goodsNo);

    @Select("select * from t_goods where id=#{id}")
    //@Select("select * from t_goods where id=#{id} for update")
    GoodsDO findById(@Param("id") Long id);

    @Insert("insert into t_goods(goodsName,originPrice,nowPrice,totalNum,balance,description,pictureUrls,status) values (#{goodsName},#{originPrice},#{nowPrice},#{totalNum},#{balance},#{description},#{pictureUrls},#{status}")
    @SelectKey(before = false, keyProperty = "id", resultType = Long.class, statementType = StatementType.STATEMENT, statement = "SELECT LAST_INSERT_ID() AS id")
    int saveGoods(GoodsDO goodsDO);

    @Update("update t_goods set goodsName=#{goodsName},originPrice=#{originPrice},nowPrice=#{nowPrice},totalNum=#{totalNum},balance=#{balance},description=#{description},pictureUrls=#{pictureUrls},status=#{status} where goodsNo=#{goodsNo}")
    int update(GoodsDO goodsDO);

    @Update("update t_goods set balance=balance-#{num},version=#{version}+1 where id=#{id} and balance-#{num}>=0 and version=#{version}")
    int reduceStock(Long id, Integer num, Integer version);
}
