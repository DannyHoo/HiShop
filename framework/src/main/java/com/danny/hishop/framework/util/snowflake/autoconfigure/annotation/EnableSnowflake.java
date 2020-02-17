package com.danny.hishop.framework.util.snowflake.autoconfigure.annotation;

import com.danny.hishop.framework.util.snowflake.autoconfigure.SnowflakeAutoConfiguration;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 引入雪花算法的注解
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(SnowflakeAutoConfiguration.class)
public @interface EnableSnowflake {
}
