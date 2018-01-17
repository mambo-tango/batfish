package org.batfish.es.repository;

import java.io.Serializable;

import org.batfish.common.domain.JobDetail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
* @author Tango
* @date 2018年1月17日 下午8:41:52
* @since 
*/
public interface JobDetailRepository extends ElasticsearchRepository<JobDetail, Serializable>{

}
