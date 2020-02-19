package com.danny.hishop.business.user.common.elasticsearch;

import com.danny.hishop.business.user.UserApplicationTests;
import com.danny.hishop.business.user.model.dto.UserDTO;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;

import java.util.List;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2020/2/19上午10:40
 */
public class ElasticsearchTemplateTest extends UserApplicationTests {


    //依赖 spring-boot-starter-data-elasticsearch
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Test
    public void elasticsearchTemplateTest() {
        //elasticsearchTemplate.createIndex("hishop");//创建index/database
        SearchQuery searchQuery = new NativeSearchQuery(QueryBuilders.matchAllQuery());
        List<UserDTO> users = elasticsearchTemplate.queryForList(searchQuery, UserDTO.class);
        System.out.println(users.toString());
    }


}
