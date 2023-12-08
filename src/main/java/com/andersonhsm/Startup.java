package com.andersonhsm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Startup {

	public static void main(String[] args) {
		var app = new SpringApplication(Startup.class);

		app.setWebApplicationType(WebApplicationType.SERVLET);
		app.run(args);
	}

}
