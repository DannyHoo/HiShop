package com.danny.hishop.business.user.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AddressEsRepository extends ElasticsearchRepository<AddressDocument, Long> {

}
