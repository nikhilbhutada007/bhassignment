package com.blueharvest.assignment;

import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * This is the main class that starts Spring Boot application. It is configured
 * to run traditional WAR deployments as well.
 * 
 * @author nbhutada
 *
 */
@SpringBootApplication(scanBasePackages = { "com.blueharvest.assignment" })
public class AssignmentApplication extends SpringBootServletInitializer {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(AssignmentApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(AssignmentApplication.class);
	}

}
