package org.batfish.analysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author Tango
 *
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class AnalysisApp  {
    
    public static void main( String[] args ) {
        SpringApplication.run(AnalysisApp.class, args);
    }
}
