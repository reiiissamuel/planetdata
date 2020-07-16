package com.reiiissamuel.planetdata.planetData.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("planet-data-properties")
public class AppConfigProperties {
	
	private String swapiServiceUrl;


	public String getSwapiServiceUrl() {
		return swapiServiceUrl;
	}

	public void setSwapiServiceUrl(String swapiServiceUrl) {
		this.swapiServiceUrl = swapiServiceUrl;
	}
	
	
}
