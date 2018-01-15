package org.batfish.analysis.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;

/**
* @author Tango
* @date 2018年1月11日 下午2:13:31
* @since 
*/
@Configuration
@PropertySource("classpath:redis.properties")
public class RedisConfig {
    
    public static final String SPLIT_VALUE_F = ";";
    public static final String SPLIT_VALUE_M = ":";
    
    @Autowired
    Environment environment;
    
    @Value("${batfish.analysis.redis.host}")
    String redisHostAndPort;
    
    @Bean(name = "testShardedJedis")
    public ShardedJedis getTestShardedJedis() {
        try {
            List<JedisShardInfo> shardList = new ArrayList<>();
//            String shardedsStr = environment.getProperty("spring.redis.shards.test");
            String shardedsStr = redisHostAndPort;
            if (shardedsStr != null) {
                for(String hostAndPorts: shardedsStr.split(SPLIT_VALUE_F)) {
                    String[] hostAndPort = hostAndPorts.split(SPLIT_VALUE_M);
                    JedisShardInfo info = new JedisShardInfo(hostAndPort[0], Integer.valueOf(hostAndPort[1]), 3000);
                    shardList.add(info);
                }
            }
           
            return new ShardedJedis(shardList);  
        } catch (Exception e) {
            throw new RuntimeException("配置文件加载异常");
        }
    }

}
