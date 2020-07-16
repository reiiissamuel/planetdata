package com.reiiissamuel.planetdata.planetData;

import java.nio.charset.Charset;

import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.reiiissamuel.planetdata.planetData.repositories.PlanetRepository;
import com.reiiissamuel.planetdata.planetData.restControllers.PlanetResource;
import com.reiiissamuel.planetdata.planetData.service.PlanetServiceSpecification;

//@SpringBootTest
@RunWith(SpringRunner.class)
@WebMvcTest
@AutoConfigureMockMvc
class PlanetDataApplicationTests {

	@MockBean
	private PlanetRepository repository;
	
	@MockBean
	private PlanetServiceSpecification service;

	@Autowired
	PlanetResource resource;

	@Autowired
	private MockMvc mockMvc;

//	@Test
//	public void whenGetAllRequest_thenCorrectResponse() throws Exception {
//		MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
//		String planet = "{\"name\": \"Terra\", \"climete\" : \"umid\", \"terrain\" : \"water\"}";
//		mockMvc.perform(
//				MockMvcRequestBuilders.get("/planets").content(planet))
//				.andExpect(MockMvcResultMatchers.status().isOk());
//				//.andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
//	}
	
	@Test
	public void whenGetByIdRequest_thenCorrectResponse() throws Exception {
		MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
		String planet = "{\"name\": \"Terra\", \"climete\" : \"umid\", \"terrain\" : \"water\"}";
		mockMvc.perform(
				MockMvcRequestBuilders.get("/planets/1").content(planet))
				.andExpect(MockMvcResultMatchers.status().isOk());
				//.andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
	}

//	@Test
//	public void whenPostRequestToUsersAndValidPlanet_thenCorrectResponse() throws Exception {
//		MediaType textPlainUtf8 = new MediaType(MediaType.TEXT_PLAIN, Charset.forName("UTF-8"));
//		String planet = "{\"name\": \"Terra\", \"climete\" : \"umid\", \"terrain\" : \"water\"}";
//		mockMvc.perform(
//				MockMvcRequestBuilders.post("/planets").content(planet).contentType(MediaType.APPLICATION_JSON_UTF8))
//				.andExpect(MockMvcResultMatchers.status().isCreated())
//				.andExpect(MockMvcResultMatchers.content().contentType(textPlainUtf8));
//	}

//	@Test
//	public void whenPostRequestToUsersAndInValidPlanet_thenCorrectResponse() throws Exception {
//	    String planet = "{\"name\": \"Terra\", \"climete\" : \"umid\", \"terrain\" : \"water\"}";
//	    mockMvc.perform(MockMvcRequestBuilders.post("/planets")
//	      .content(planet)
//	      .contentType(MediaType.APPLICATION_JSON_UTF8))
//	      .andExpect(MockMvcResultMatchers.status().isBadRequest())
//	      .andExpect(MockMvcResultMatchers.jsonPath("$.name", Is.is("O campo Name é obrigatótio")))
//	      .andExpect(MockMvcResultMatchers.content()
//	        .contentType(MediaType.APPLICATION_JSON_UTF8));
//	    }


}
