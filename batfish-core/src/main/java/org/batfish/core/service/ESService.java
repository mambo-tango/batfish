package org.batfish.core.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author Tango
* @date 2018年1月18日 下午2:54:38
* @since 
*/
@Service
@FeignClient(value="batfish-es-service")
public interface ESService {
    
    @RequestMapping(value="/v1/save", method=RequestMethod.POST)
    String saveToES(@RequestParam(value="url")String url, @RequestParam(value="deatil")String detail);
}
