package com.danny.hishop.business.goods.elasticsearch;

import com.danny.hishop.business.goods.GoodsApplicationTests;
import com.danny.hishop.business.goods.GoodsApplicationTests;
import com.danny.hishop.business.goods.elasticsearch.GoodsDocument;
import com.danny.hishop.business.goods.elasticsearch.GoodsEsRepository;
import com.danny.hishop.framework.util.DateUtils;
import com.danny.hishop.framework.util.RandomValueUtil;
import com.danny.hishop.framework.util.StringUtil;
import com.danny.hishop.framework.util.snowflake.autoconfigure.core.Snowflake;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class ElasticSearchRepositoryTest extends GoodsApplicationTests {

    @Autowired
    private Snowflake snowflake;

    @Autowired
    private GoodsEsRepository goodsEsRepository;

    /**
     * 插入数据
     * 根据ID查询数据，返回实体
     */
    @Test
    public void saveTest() {
        GoodsDocument goodsInsertData = getGoods();
        GoodsDocument goodsInsertResult = goodsEsRepository.save(goodsInsertData);
        Optional<GoodsDocument> goodsQueryResult = Optional.empty();
        if (goodsEsRepository.existsById(goodsInsertData.getId())) {
            goodsQueryResult = goodsEsRepository.findById(goodsInsertData.getId());
            if (goodsQueryResult.isPresent()) {
                printResult("结果为：", goodsQueryResult.get());
            }
        }
        Assert.assertNotNull(goodsQueryResult.get());
    }


    /**
     * 根据字段查询数据，返回列表
     */


    private GoodsDocument getGoods() {
        GoodsDocument goodsDocument = new GoodsDocument();
        goodsDocument.setGoodsNo("G" + snowflake.genId() + StringUtil.getRandomNum(5))
                .setGoodsName("商品" + StringUtil.getRandomNum(6))
                .setOriginPrice(BigDecimal.valueOf(200))
                .setNowPrice(BigDecimal.valueOf(168))
                .setTotalNum(1000)
                .setBalance(900)
                .setDescription("这是商品描述")
                .setPictureUrls("http://www.baidu.com/pic1.png")
                .setStatus(10)
                .setId(snowflake.genId())
                .setCreateTime(DateUtils.getNowDate())
                .setUpdateTime(DateUtils.getNowDate());
        return goodsDocument;
    }


}
