package com.api.pinapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
public class PinappApplication {

	public static void main(String[] args) {
		SpringApplication.run(PinappApplication.class, args);
	}

}
