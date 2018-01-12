package org.batfish.analysis.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* @author Tango
* @date 2018年1月10日 下午1:21:42
* @since 
*/

@RestController
//@RequestMapping(value="/analysis")
public class AnalysisController {
    
    Logger LOG = LoggerFactory.getLogger(AnalysisController.class);

    @RequestMapping(value="/analysis", method=RequestMethod.POST)
    public String analysisTechnology(@RequestParam(value="url") String url, @RequestParam(value="detail") String detail) {
        LOG.error("url===> "+ url + " detail==> " + detail);
        return "receive the msg !";
    }
    
//    @Value("${batfish.analysis.redis.host}")
//    String redisHost;
//    
//    @RequestMapping(value = "/redis")
//    public String redis(){
//        return redisHost;
//    }
    @Value("${batfish.analysis.redis.host}")
    String foo;
    
    @RequestMapping(value = "/hi")
    public String hi(){
        System.out.println("=========================================> "+foo);
        System.out.println("=========================================> "+foo);
        System.out.println("=========================================> "+foo);
        System.out.println("=========================================> "+foo);
        return foo;
    }
}
