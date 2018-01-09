package org.batfish.webmagic.service;
/**
* @author Tango
* @date 2017年12月15日 下午4:44:11
* @since 
*/

import java.io.UnsupportedEncodingException;
import java.util.List;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

@Component
public class JobSearchProcessor implements PageProcessor{
    
    Logger LOG = LoggerFactory.getLogger(JobSearchProcessor.class);
    
    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);
    
    @Autowired
    DefaultMQProducer producer;

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
                tstack = tstack.split("mt10")[0];
                tstack = tstack.toLowerCase().replaceAll("<br>", "").replaceAll("&nbsp;", "").replaceAll("<div class=\"bmsg job_msg inbox\">", "").replaceAll("<div class=\"", "");
                compny = compny.toLowerCase();
                String compnyhref = page.getRequest().getUrl();
                String compnyName = compny.substring(compny.indexOf("title=\""), compny.indexOf("<em")).split("\">")[1];
                
                String msgbody = compnyhref + "@&" + compnyName + "@&" + tstack;
                try {
                    Message msg = new Message("testTopic", "testTag", compnyhref, 0, msgbody.getBytes(RemotingHelper.DEFAULT_CHARSET), true);
                    producer.send(msg, new SendCallback() {
                        
                        @Override
                        public void onSuccess(SendResult sendResult) {
                            LOG.info("发送成功：href:" + compnyhref + " name:" + compnyName);
                            
                        }
                        
                        @Override
                        public void onException(Throwable e) {
                            LOG.error("发送失败" + e.getMessage(), e);
                        }
                    });
                } catch (UnsupportedEncodingException e) {
                    LOG.error(e.getMessage(), e);
                } catch (MQClientException e) {
                    // TODO Auto-generated catch block
                    LOG.error(e.getMessage(), e);
                } catch (RemotingException e) {
                    // TODO Auto-generated catch block
                    LOG.error(e.getMessage(), e);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    LOG.error(e.getMessage(), e);
                }
                
//                if(tstack.contains("redis")
//                        && (tstack.contains("rocket") || tstack.contains("rocketmq"))
//                        && (tstack.contains("boot") || tstack.contains("cloud"))) {
//                    
//                    page.putField("compny", page.getHtml().css("p.cname").toString());
//                    LOG.error("compny =======> "+ compny);
//                    LOG.error("MONY:"+page.getHtml().css("cn.strong")+"  targetUrl: "+ page.getRequest().getUrl());
//                    LOG.error("tstack =======> "+ tstack);
//                }
                
            }
        }
        
        
    }

}
