package org.batfish.eureka.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Tango
 *
 */

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApp  {
    
    public static void main( String[] args ) {
        SpringApplication.run(EurekaServerApp.class, args);
    }
}
