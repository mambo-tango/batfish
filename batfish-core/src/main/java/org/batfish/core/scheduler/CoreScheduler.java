//package org.batfish.core.scheduler;
//
//import org.batfish.core.service.AnalysisTechnologyService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.EnableScheduling;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Component;
//
///**
//* @author Tango
//* @date 2018年1月10日 下午2:32:10
//* @since 
//*/
//
//@Deprecated
//@Component
//@EnableScheduling
//public class CoreScheduler {
//    
//    Logger LOG = LoggerFactory.getLogger(CoreScheduler.class);
//    
//    @Autowired
//    AnalysisTechnologyService analysisService;
//    
//    @Scheduled(fixedRate=5000)
//    void testScheduler() {
//        try {
//            String result = analysisService.analysisTechnology("tangoUrl", "tangoDetails");
//            System.out.println("result ====================================> "+result);
//        } catch (Exception e) {
//            LOG.error(e.getMessage(), e);
//        }
//    }
//
//}
