package org.batfish.es.service.impl;

import org.batfish.common.domain.JobDetail;
import org.batfish.es.repository.JobDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author Tango
* @date 2018年1月17日 下午8:46:38
* @since 
*/

@Service
public class JobDetailService implements org.batfish.es.service.JobDetailService {

    @Autowired
    JobDetailRepository jobDetailRepository;
    
    @Override
    public String saveJobDetail(JobDetail jobDetail) {
        // TODO Auto-generated method stub
        return jobDetailRepository.save(jobDetail).getUrl();
    }

}
