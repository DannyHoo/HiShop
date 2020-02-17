package com.danny.hishop.framework.config;

import com.danny.hishop.framework.lock.RedissonLockUtils;
import com.danny.hishop.framework.lock.RedissonLocker;
import io.micrometer.core.instrument.util.StringUtils;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.ClusterServersConfig;
import org.redisson.config.Config;
import org.redisson.config.SentinelServersConfig;
import org.redisson.config.SingleServerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Iterator;

/**
 */
@Configuration
public class RedissonConfig {

    @Autowired
    private RedisProperties redisProperties;

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson() {
        return Redisson.create(getConfig(redisProperties));
    }

    @Bean
    public RedissonLocker redissonLocker(RedissonClient redissonClient) {
        RedissonLocker locker = new RedissonLocker(redissonClient);
        RedissonLockUtils.setLocker(locker);
        return locker;
    }

    public Config getConfig(RedisProperties redisProperty) {
        Config config = new Config();
        if (redisProperty.getSentinel() != null) {
            //sentinel
            SentinelServersConfig sentinelServersConfig = config.useSentinelServers();
            sentinelServersConfig.setMasterName(redisProperty.getSentinel().getMaster());
            redisProperty.getSentinel().getNodes();
            //
            sentinelServersConfig.addSentinelAddress(redisProperty.getSentinel().getNodes().toArray(new String[0]));
            sentinelServersConfig.setDatabase(redisProperty.getDatabase());
            if (redisProperty.getPassword() != null) {
                sentinelServersConfig.setPassword(redisProperty.getPassword());
            }
        } else if (redisProperty.getCluster() != null) {
            //集群
            ClusterServersConfig clusterServersConfig = config.useClusterServers();
            String address;
            for (Iterator iterator = redisProperty.getCluster().getNodes().iterator(); iterator.hasNext(); clusterServersConfig.addNodeAddress(address)) {
                address = (String) iterator.next();
                if (StringUtils.isNotBlank(address) && !address.contains("redis")) {
                    address = "redis://" + address;
                }
            }
            if (redisProperty.getPassword() != null) {
                clusterServersConfig.setPassword(redisProperty.getPassword());
            }

        } else {
            //single server
            SingleServerConfig singleServerConfig = config.useSingleServer();
            // format as redis://127.0.0.1:7181 or rediss://127.0.0.1:7181 for SSL
            String schema = redisProperty.isSsl() ? "rediss://" : "redis://";
            singleServerConfig.setAddress(schema + redisProperty.getHost() + ":" + redisProperty.getPort());
            singleServerConfig.setDatabase(redisProperty.getDatabase());
            //
            if (redisProperty.getPassword() != null) {
                singleServerConfig.setPassword(redisProperty.getPassword());
            }
            singleServerConfig.setConnectionPoolSize(redisProperty.getJedis().getPool().getMaxActive());
            singleServerConfig.setConnectionMinimumIdleSize(redisProperty.getJedis().getPool().getMinIdle());
        }
        return config;
    }

    public void initData() {
        System.out.println("fdfsdfsdf");
    }
}