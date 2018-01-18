package org.batfish.es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * es
 *
 */

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
public class ESApp {
    
    public static void main( String[] args ) {
        SpringApplication.run(ESApp.class, args);
    }
}
