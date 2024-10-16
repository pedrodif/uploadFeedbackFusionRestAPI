package com.feedbackFusion.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages =  {"com.feedbackFusion"})
@EntityScan("com.feedbackFusion")
@EnableJpaRepositories(basePackages = "com.feedbackFusion")
public class FeedbackFusionRestApiApplication {
	public static void main(String[] args) {
		SpringApplication.run(FeedbackFusionRestApiApplication.class, args);
	}
}
