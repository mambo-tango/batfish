package org.batfish.es.repository;

import org.batfish.es.domain.JobDetail;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
* @author Tango
* @date 2018年1月17日 下午8:41:52
* @since 
*/
@Repository
public interface JobDetailRepository extends ElasticsearchRepository<JobDetail, String>{

}
