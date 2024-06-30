package com.fiapst1.os_service;

import com.netflix.discovery.EurekaNamespace;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EnableDiscoveryClient
public class OsServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OsServiceApplication.class, args);
	}

}
