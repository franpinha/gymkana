package com.appfilm.film;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class EmergyaFilmsApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(EmergyaFilmsApplication.class, args);
	}
	
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		
		return application.sources(EmergyaFilmsApplication.class);
	}
}
