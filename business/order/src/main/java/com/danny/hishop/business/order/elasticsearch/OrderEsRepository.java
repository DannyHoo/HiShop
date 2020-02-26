package com.danny.hishop.business.order.elasticsearch;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * 依赖 spring-boot-starter-data-elasticsearch
 */
public interface OrderEsRepository extends ElasticsearchRepository<OrderDocument, Long> {

    List<OrderDocument> findByOrderNo(String orderNo);
    List<OrderDocument> findByUserName(String userName);
    List<OrderDocument> findByReceiverName(String receiverName);
    List<OrderDocument> findByReceiverMobileNo(String receiverMobileNo);
    List<OrderDocument> findByReceiverAddress(String receiverAddress);

}
