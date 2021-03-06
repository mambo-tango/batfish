package org.batfish.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * Tango
 *
 */

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class CoreApp {
    
    public static void main( String[] args ) {
        SpringApplication.run(CoreApp.class, args);
    }
}
