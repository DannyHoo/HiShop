package com.danny.hishop.framework.util;

import com.danny.hishop.framework.util.snowflake.autoconfigure.core.Snowflake;

/**
 *
 * @date 2020/4/17上午11:26
 */
public class IdUtils {

    public static String generateOrderNo(Snowflake snowflake){
        return new StringBuffer("OD").append(snowflake.genId()).toString();
    }

}
