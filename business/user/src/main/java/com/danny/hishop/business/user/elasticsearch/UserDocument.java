package com.danny.hishop.business.user.elasticsearch;

import com.danny.hishop.framework.model.dto.BaseDTO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@Accessors(chain = true)
@Document(indexName = "hishop-user-user")
public class UserDocument extends BaseDTO {
    private String userName;
    private String mobileNo;
    private String salt;
    private String password;
    private String email;
    private String realName;
    private String idCardNo;
}
