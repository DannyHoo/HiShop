package com.danny.hishop.business.user.dao;

import com.danny.hishop.business.user.model.dto.UserDTO;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;
import java.util.Optional;

/**
 * 依赖 spring-boot-starter-data-elasticsearch
 */
public interface UserEsRepository extends ElasticsearchRepository<UserDTO, Long> {

    List<UserDTO> findByUserName(String userName);
    List<UserDTO> findByMobileNo(String mobileNo);
    List<UserDTO> findByIdCardNo(String idCardNo);
    List<UserDTO> findByRealName(String realName);
    List<UserDTO> findByComment(String comment);

}
