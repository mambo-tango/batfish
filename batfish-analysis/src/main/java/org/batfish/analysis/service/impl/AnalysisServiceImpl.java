package org.batfish.analysis.service.impl;

import org.batfish.analysis.service.AnalysisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import redis.clients.jedis.ShardedJedis;

/**
* @author Tango
* @date 2018年1月15日 上午8:57:11
* @since 
*/
@Component
public class AnalysisServiceImpl implements AnalysisService{
    
    @Autowired
    @Qualifier("testShardedJedis")
    ShardedJedis redis;
    
    @Value("${batfish.analysis.type.array}")
    String typeArray;

    @Override
    public String saveAnalysisToRedis(String msg) {
        Long result = 0L;
        for(String type: typeArray.split(",")) {
            if(msg.contains(type)) {
                result = result + redis.incr(type);
                System.out.println(">>>>>>>>>>>>自增1<<<<<<<<<<<"+ type);
            }
        }
        return String.valueOf(result);
    }
    
    
    
}
