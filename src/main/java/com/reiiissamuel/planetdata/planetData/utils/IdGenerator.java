package com.reiiissamuel.planetdata.planetData.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class IdGenerator {
	
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
	
	
	public String getNewId(String source) {
		String now = dtf.format(LocalDateTime.now()).replaceAll("/", "").replaceAll(":", "");
		
		return now.split(" ")[0] + source.replaceAll(" ", "") + now.split(" ")[1];
		
	}

}
