package org.batfish.webmagic.service;
/**
* @author Tango
* @date 2017年12月15日 下午4:44:11
* @since 
*/

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class JobSearchProcessor implements PageProcessor{
    
    Logger LOG = LoggerFactory.getLogger(JobSearchProcessor.class);
    
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public Site getSite() {
        // TODO Auto-generated method stub
        return site;
    }

    @Override
    public void process(Page page) {
        
        List<String> allUrlList = page.getHtml().css("div.el").links().regex("(http://jobs\\.51job\\.com/shenzhen-\\w{3}/\\d+\\.html)").all();
        allUrlList.addAll(page.getHtml().css("div.el").links().regex("(http://jobs\\.51job\\.com/shenzhen/\\d+\\.html)").all());
        page.addTargetRequests(allUrlList);
        
        if(page.getHtml().css("p.cname") != null) {
            String tstack = page.getHtml().css("div.job_msg").toString();
            String compny = page.getHtml().css("p.cname").toString();
            if(tstack != null && compny != null) {
                tstack = tstack.toLowerCase().replaceAll("<br>", "").replaceAll("&nbsp;", "");
                compny = compny.toLowerCase();
                LOG.warn("compny =======> "+ compny);
                LOG.warn("tstack =======> "+ tstack);
                if(tstack.contains("redis")
                        && (tstack.contains("rocket") || tstack.contains("rocketmq"))
                        && (tstack.contains("boot") || tstack.contains("cloud"))) {
                    
                    page.putField("compny", page.getHtml().css("p.cname").toString());
                    LOG.error("compny =======> "+ compny);
                    LOG.error("MONY:"+page.getHtml().css("cn.strong")+"  targetUrl: "+ page.getRequest().getUrl());
                    
//                  page.putField("tstack", page.getHtml().css("div.job_msg").toString());
                    LOG.error("tstack =======> "+ tstack);
            }
                
            }
        }
        
        
    }

}
