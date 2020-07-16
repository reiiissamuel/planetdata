package com.reiiissamuel.planetdata.planetData.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import com.reiiissamuel.planetdata.planetData.utils.IdGenerator;


@Configuration
public class BeansConfig {
	
	@Bean
	public AppConfigProperties appConfig() {
		return new AppConfigProperties();
	}
	
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	@Bean
	public IdGenerator generator() {
		return new IdGenerator();
	}

}
