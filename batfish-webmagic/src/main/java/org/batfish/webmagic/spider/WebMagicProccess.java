package org.batfish.webmagic.spider;

import org.batfish.webmagic.service.JobSearchProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Spider;

/**
* @author Tango
* @date 2017年12月15日 下午4:42:44
* @since 
*/
@Component
public class WebMagicProccess {
    
    Logger LOG = LoggerFactory.getLogger(WebMagicProccess.class);
    
    @Autowired
    JobSearchProcessor gitrep;

    public void createSpider(String rooturl) {
        Spider.create(gitrep).addUrl(rooturl).thread(1).run();
        
    }


}
