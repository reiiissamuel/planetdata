package com.reiiissamuel.planetdata.planetData.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.reiiissamuel.planetdata.planetData.config.AppConfigProperties;

@Service
public class StarWarsApiService {
	
private static final Logger log = LoggerFactory.getLogger(StarWarsApiService.class);
	
	@Autowired
	private AppConfigProperties configProperties;
	
	@Autowired
	private RestTemplate restTemplate;
	 
	public int getPlanetInfoFromSwApi(String planetName){
		String uri = configProperties.getSwapiServiceUrl() + "?search="+planetName;
		log.info("Planet info from: url='{}'", configProperties.getSwapiServiceUrl());

		ResponseEntity<String> response = restTemplate.getForEntity(uri, String.class);
		try {
			if (response.getBody() != null) {
				JSONObject jsonObject = new JSONObject(response.getBody());
				JSONArray jsonArray = jsonObject.getJSONArray("results");
				for (int i = 0; i < jsonArray.length(); i++) {
					//System.out.println(jsonArray.getJSONObject(i).getString("name")); // display usernames
					String[] filmsUrls = jsonArray.getJSONObject(i).getString("films").substring(1, jsonArray.getJSONObject(i).getString("films").length() - 2).split(",");
					return filmsUrls.length;
				}
			}
			return 0;
		} catch (JSONException e) {
			e.printStackTrace();
			return 0;
		}
	}
	

}
