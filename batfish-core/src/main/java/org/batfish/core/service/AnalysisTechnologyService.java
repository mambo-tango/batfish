package org.batfish.core.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author Tango
* @date 2018年1月10日 下午1:49:00
* @since 
*/

@Service
@FeignClient(value="analysis-service")
public interface AnalysisTechnologyService {
    
    @RequestMapping(value="/analysis", method=RequestMethod.POST)
    String analysisTechnology(@RequestParam(value="url") String url, @RequestParam(value="detail") String detail);
           
}
