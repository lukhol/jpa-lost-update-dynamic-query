package com.lukhol.hibernate.lostupdate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lukhol") // Without this tests fail because of not visible services
public class LostUpdateApplication {

	public static void main(String[] args) {
		SpringApplication.run(LostUpdateApplication.class, args);
	}

}

