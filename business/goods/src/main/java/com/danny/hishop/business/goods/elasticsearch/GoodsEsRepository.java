package com.danny.hishop.business.goods.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface GoodsEsRepository  extends ElasticsearchRepository<GoodsDocument, Long> {

}
