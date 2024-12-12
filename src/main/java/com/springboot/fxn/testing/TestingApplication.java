package com.springboot.fxn.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TestingApplication {

	// Create a logger instance
	//private static final Logger logger = LoggerFactory.getLogger(TestingApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(TestingApplication.class, args);

		// Print Line
		//System.out.println("Hello, World!");

		// Log messages to console
		//logger.info("Application has started successfully!");
		//logger.debug("Debugging application start process...");
		//logger.error("Sample error message");
	}

}
