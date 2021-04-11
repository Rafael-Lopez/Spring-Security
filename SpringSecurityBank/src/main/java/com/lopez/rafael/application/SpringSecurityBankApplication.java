package com.lopez.rafael.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableJpaRepositories("com.lopez.rafael.repository")
@EntityScan("com.lopez.rafael.model")
@ComponentScans({
		@ComponentScan("com.lopez.rafael.controller"),
		@ComponentScan("com.lopez.rafael.config")
})
@EnableWebSecurity(debug = true)
public class SpringSecurityBankApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityBankApplication.class, args);
	}

}
