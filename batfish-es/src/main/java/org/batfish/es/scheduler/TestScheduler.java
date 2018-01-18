package org.batfish.es.scheduler;

import java.util.Date;
import java.util.UUID;

import org.batfish.es.domain.JobDetail;
import org.batfish.es.service.JobDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
* @author Tango
* @date 2018年1月18日 下午3:37:39
* @since 
*/
@Component
@EnableScheduling
public class TestScheduler {
    
    @Autowired
    JobDetailService service;
    
    @Scheduled(fixedRate=100000000)
    private void test() {
        JobDetail jobDetail = new JobDetail();
        jobDetail.setId(UUID.randomUUID().toString());
        jobDetail.setCreateTime(new Date());
        jobDetail.setCompny("testCompny");
        jobDetail.setUrl("testUrl");
        jobDetail.setDetail("testDetail");
        service.saveJobDetail(jobDetail);
    }

}
