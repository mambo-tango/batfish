package org.batfish.webmagic.shcheduler;

import org.batfish.webmagic.spider.WebMagicProccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
* @author Tango
* @date 2017年12月17日 上午11:49:43
* @since 
*/

@Component
@EnableScheduling
public class JOBSpiderScheduler {
    
    @Autowired
    Environment ev;
    
    @Autowired
    WebMagicProccess wmp;
    
    @Scheduled(fixedRate=1000000000)
    public void shedulerByPage() {
        
//        String urlpreffix = ev.getProperty("job.search.root.preffix.url");
//        String urlsuffix = ev.getProperty("job.search.root.suffix.url");
        
        String urlpreffix = "http://search.51job.com/list/040000,000000,0000,00,9,08%252C09,java,2,";
        String urlsuffix = ".html?lang=c&stype=1&postchannel=0000&workyear=99&cotype=99&degreefrom=99&jobterm=99&companysize=99&lonlat=0%2C0&radius=-1&ord_field=0&confirmdate=9&fromType=&dibiaoid=0&address=&line=&specialarea=00&from=&welfare=";
        
        for(int i=1; i<57; i++) {
            wmp.createSpider(urlpreffix + i + urlsuffix);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
    }

}
