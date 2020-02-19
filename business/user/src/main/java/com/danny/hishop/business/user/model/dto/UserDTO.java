package com.danny.hishop.business.user.model.dto;

import com.danny.hishop.framework.model.dto.BaseDTO;
import com.danny.hishop.framework.model.model.BaseDO;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @author huyuyang
 * @email yuyang.hu@opay-inc.com
 * @date 2019/11/26下午9:41
 */
@Data
@Accessors(chain = true)

@Document(indexName = "hishop", type = "user")
public class UserDTO extends BaseDTO {
    private String userName;
    private String mobileNo;
    private String salt;
    private String password;
    private String email;
    private String realName;
    private String idCardNo;
}
