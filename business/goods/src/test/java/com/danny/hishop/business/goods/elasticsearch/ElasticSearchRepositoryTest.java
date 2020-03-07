package com.danny.hishop.business.goods.elasticsearch;

import com.danny.hishop.business.goods.GoodsApplicationTests;
import com.danny.hishop.business.goods.GoodsApplicationTests;
import com.danny.hishop.business.goods.elasticsearch.GoodsDocument;
import com.danny.hishop.business.goods.elasticsearch.GoodsEsRepository;
import com.danny.hishop.framework.util.DateUtils;
import com.danny.hishop.framework.util.RandomValueUtil;
import com.danny.hishop.framework.util.StringUtil;
import com.danny.hishop.framework.util.snowflake.autoconfigure.core.Snowflake;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.*;

public class ElasticSearchRepositoryTest extends GoodsApplicationTests {

    @Autowired
    private Snowflake snowflake;

    @Autowired
    private GoodsEsRepository goodsEsRepository;

    static List<String> list = new ArrayList<>();

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
                .setGoodsName("商品—" + StringUtil.getRandomNum(6))
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


    @Test
    public void saveTest2() throws IOException {
        StringBuffer stringBuffer=new StringBuffer();
        int id=107;
        //String[] keywords = new String[]{"手机", "衣服", "食品", "电脑", "母婴", "电器", "美妆", "男鞋", "女鞋"};
        String[] keywords = new String[]{"衣服"};
        for (int j = 0; j < keywords.length; j++) {
            for (int i = 1; i < 2; i++) {
                //https://search.jd.com/Search?keyword=%E6%89%8B%E6%9C%BA9
                String url = "https://search.jd.com/Search?keyword=" + keywords[j] + "&enc=utf-8&psort=3&page=" + (i*2-1);//第二页商品
                //String url = "https://search.jd.com/Search?keyword=" + keywords[j] + "&enc=utf-8&psort=3&page=" + i;//第二页商品
                //String url = "https://search.jd.com/Search?keyword=衣服&enc=utf-8&psort=3&page="+i;//第二页商品
                //网址分析
                /*keyword:关键词（京东搜索框输入的信息）
                 * enc：编码方式（可改动:默认UTF-8）
                 * psort=3 //搜索方式  默认按综合查询 不给psort值
                 * page=分业（不考虑动态加载时按照基数分业，每一页30条，这里就不演示动态加载）
                 * 注意：受京东商品个性化影响，准确率无法保障
                 * */
                org.jsoup.nodes.Document doc = Jsoup.connect(url).maxBodySize(0).get();
                //doc获取整个页面的所有数据
                Elements ulList = doc.select("ul[class='gl-warp clearfix']");
                Elements liList = ulList.select("li[class='gl-item']");
                //循环liList的数据
                for (Element item : liList) {
                    //排除广告位置
                    if (!item.select("span[class='p-promo-flag']").text().trim().equals("广告")) {
                        String title=item.select("div[class='p-name p-name-type-2']").select("em").text();
                        String price=item.select("div[class='p-price']").select("i").text();
                        String image="https:"+item.select("div[class='p-img']").select("a").select("img").attr("source-data-lazy-img");

                        stringBuffer.append("INSERT INTO `product` VALUES ('")
                                .append(id++)
                                .append("', '3', '")
                                .append(title)
                                .append("', '2', '这是衣服品牌', '', '")
                                .append(image)
                                .append("', '1', '这是供应商1', '")
                                .append(price)
                                .append("', '20.00', '323', '0', '0', '50.00', '200', '1', '")
                                .append(DateUtils.getNewFormatDateString(new Date()))
                                .append("', '")
                                .append(DateUtils.getNewFormatDateString(new Date()))
                                .append("', '0');")
                                .append("\n");

                        //如果向存到数据库和文件里请自行更改
                        System.out.println(title+" "+price+" "+image);//打印商品标题到控制台
                        list.add(item.select("div[class='p-name p-name-type-2']").select("em").text());
                        GoodsDocument goodsInsertData = getGoods(item.select("div[class='p-name p-name-type-2']").select("em").text());
                        GoodsDocument goodsInsertResult = goodsEsRepository.save(goodsInsertData);
                    }
                }
            }
            System.out.println("==================="+list.size());
        }
        System.out.println(stringBuffer.toString());
    }

    @Test
    public void saveTest1() throws IOException {
        String[] keywords = new String[]{"手机", "衣服", "食品", "电脑", "母婴", "电器", "美妆", "男鞋", "女鞋"};
        for (int j = 0; j < keywords.length; j++) {
            for (int i = 1; i < 30; i++) {
                String url = "https://search.jd.com/Search?keyword=" + keywords[j] + "&enc=utf-8&psort=3&page=" + i;//第二页商品
                //String url = "https://search.jd.com/Search?keyword=衣服&enc=utf-8&psort=3&page="+i;//第二页商品
                //网址分析
                /*keyword:关键词（京东搜索框输入的信息）
                 * enc：编码方式（可改动:默认UTF-8）
                 * psort=3 //搜索方式  默认按综合查询 不给psort值
                 * page=分业（不考虑动态加载时按照基数分业，每一页30条，这里就不演示动态加载）
                 * 注意：受京东商品个性化影响，准确率无法保障
                 * */
                org.jsoup.nodes.Document doc = Jsoup.connect(url).maxBodySize(0).get();
                //doc获取整个页面的所有数据
                Elements ulList = doc.select("ul[class='gl-warp clearfix']");
                Elements liList = ulList.select("li[class='gl-item']");
                //循环liList的数据
                for (Element item : liList) {
                    //排除广告位置
                    if (!item.select("span[class='p-promo-flag']").text().trim().equals("广告")) {
                        //如果向存到数据库和文件里请自行更改
                        System.out.println(item.select("div[class='p-name p-name-type-2']").select("em").text());//打印商品标题到控制台
                        list.add(item.select("div[class='p-name p-name-type-2']").select("em").text());
                        GoodsDocument goodsInsertData = getGoods(item.select("div[class='p-name p-name-type-2']").select("em").text());
                        GoodsDocument goodsInsertResult = goodsEsRepository.save(goodsInsertData);
                    }
                }
            }
            System.out.println("==================="+list.size());
        }

    }

    private GoodsDocument getGoods(String goodsName) {
        GoodsDocument goodsDocument = new GoodsDocument();
        goodsDocument.setGoodsNo("G" + snowflake.genId() + StringUtil.getRandomNum(5))
                .setGoodsName(goodsName)
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
