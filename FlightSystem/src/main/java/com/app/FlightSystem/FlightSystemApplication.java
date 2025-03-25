package com.app.FlightSystem;

import org.springframework.boot.SpringApplication;
//import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class FlightSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(FlightSystemApplication.class, args);
	}

}
