package com.bichi.resttemplate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class SpringResttemplateApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringResttemplateApplication.class, args);
	}

}
