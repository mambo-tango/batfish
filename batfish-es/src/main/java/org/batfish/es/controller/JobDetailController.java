package org.batfish.es.controller;

import java.util.UUID;

import org.batfish.es.domain.JobDetail;
import org.batfish.es.service.JobDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
* @author Tango
* @date 2018年1月17日 下午9:03:56
* @since 
*/
@RestController
@RequestMapping(value="/v1")
public class JobDetailController {
    
    @Autowired
    JobDetailService jobDetailService;
    
    @RequestMapping(value="save", method=RequestMethod.POST)
    String saveJobDetail(@RequestParam("url") String url, @RequestParam("detail") String detail) {
        
        JobDetail jobDetail = new JobDetail();
        jobDetail.setId(UUID.randomUUID().toString());
        jobDetail.setUrl(url);
        jobDetail.setDetail(detail);
        return jobDetailService.saveJobDetail(jobDetail);
    }

}
