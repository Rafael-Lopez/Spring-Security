package com.lopez.rafael.SpringSecurityBank;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("com.lopez.rafael.controller")
public class SpringSecurityBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBankApplication.class, args);
	}

}
