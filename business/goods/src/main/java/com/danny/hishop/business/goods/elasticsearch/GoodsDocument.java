package com.danny.hishop.business.goods.elasticsearch;

import com.danny.hishop.framework.model.dto.BaseDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.lang.RandomStringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.data.elasticsearch.annotations.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Data
@Accessors(chain = true)
@Document(indexName = "hishop-goods-goods")
public class GoodsDocument extends BaseDTO {
    /* 商品编号 */
    private String goodsNo;
    /* 商品名称 */
    private String goodsName;
    /* 原价 */
    private BigDecimal originPrice;
    /* 现价 */
    private BigDecimal nowPrice;
    /* 商品总数量 */
    private int totalNum;
    /* 剩余数量 */
    private int balance;
    /*  */
    private String description;
    /*  */
    private String pictureUrls;
    /* 商品状态 10正常 20下架 */
    private Integer status;

    static List<String> list = new ArrayList<>();

    //https://www.cnblogs.com/hzb462606/p/9195602.html
    public static void main(String[] args) throws Exception {
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
                    }
                }
            }
            System.out.println("==================="+list.size());
        }

    }
}
