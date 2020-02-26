package com.danny.hishop.business.user.elasticsearch;

import com.danny.hishop.framework.model.model.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;


@Data
@Accessors(chain = true)
@Document(indexName = "hishop-user-address")
public class AddressDocument extends BaseDO {
    private String userName;
    private String receiverName;
    private String receiverMobileNo;
    private String receiverAddress;
    private Integer isDefault;
}
