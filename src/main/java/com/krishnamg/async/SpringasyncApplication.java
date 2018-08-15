package com.krishnamg.async;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.web.client.RestTemplate;

import java.util.concurrent.Executor;

@SpringBootApplication
@EnableAsync
public class SpringasyncApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringasyncApplication.class, args).close();
	}

	@Bean
	public Executor asyncExecutor(){
		ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(2);
		executor.setMaxPoolSize(2);
		executor.setQueueCapacity(500);
		executor.setThreadNamePrefix("GithubLookup-");
		executor.initialize();
		return executor;
	}
	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
}
