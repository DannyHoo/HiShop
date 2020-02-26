package com.danny.hishop.business.user.elasticsearch;

import com.danny.hishop.business.user.elasticsearch.UserDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

/**
 * 依赖 spring-boot-starter-data-elasticsearch
 */
public interface UserEsRepository extends ElasticsearchRepository<UserDocument, Long> {

    List<UserDocument> findByUserName(String userName);
    List<UserDocument> findByMobileNo(String mobileNo);
    List<UserDocument> findByIdCardNo(String idCardNo);
    List<UserDocument> findByRealName(String realName);
    List<UserDocument> findByComment(String comment);

}
