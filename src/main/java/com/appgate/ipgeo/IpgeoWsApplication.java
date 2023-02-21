package com.appgate.ipgeo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class IpgeoWsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IpgeoWsApplication.class, args);
	}

}
