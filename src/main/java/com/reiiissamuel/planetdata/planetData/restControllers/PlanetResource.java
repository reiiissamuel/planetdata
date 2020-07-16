package com.reiiissamuel.planetdata.planetData.restControllers;

import java.util.LinkedList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.util.UriComponentsBuilder;

import com.reiiissamuel.planetdata.planetData.entity.Planet;
import com.reiiissamuel.planetdata.planetData.exceptions.NotAStarWarsPlanetException;
import com.reiiissamuel.planetdata.planetData.exceptions.PlanetExistsException;
import com.reiiissamuel.planetdata.planetData.exceptions.PlanetNotFoundException;
import com.reiiissamuel.planetdata.planetData.exceptions.PlanetnameNotFoundException;
import com.reiiissamuel.planetdata.planetData.service.PlanetServiceSpecification;
import com.reiiissamuel.planetdata.planetData.service.StarWarsApiService;
import com.reiiissamuel.planetdata.planetData.utils.IdGenerator;

@Validated 
@RestController
@RequestMapping(value = "/planets")
public class PlanetResource {
	
	@Autowired
	StarWarsApiService swapiService;
	
	@Autowired
    private PlanetServiceSpecification service;
	
	@Autowired
	IdGenerator idGenerator;
	
	 @PostMapping
	    public ResponseEntity<Void> addPlanet(@Valid @RequestBody Planet planet, UriComponentsBuilder builder) {
		 
		 	planet.setFilmAppearancesqtt(swapiService.getPlanetInfoFromSwApi(planet.getName()));
		 	planet.setId(idGenerator.getNewId(planet.getName()));
		 	
	    	try {
				String id = service.addPlanet(planet).getPlanetId();
				HttpHeaders headers = new HttpHeaders();
				headers.setLocation(builder.path("/planets/{id}").buildAndExpand(id).toUri());
				return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
			} catch (PlanetExistsException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			} catch (NotAStarWarsPlanetException e) {
				throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
			}
	    }
	
	@GetMapping
    public List<String> getAllPlanets() {
		List<Planet> planets = service.findAll();
		List<String> planetsLinks = new LinkedList<>();
		for(Planet planet : planets) {
			//self link generating
			Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(planet.getPlanetId()).withSelfRel();
			planetsLinks.add(selfLink.getHref());
		}
	      return planetsLinks;	
    }

    @GetMapping( "/{planetId}")
    public Resource<Planet> getPlanetByPlanetId(@PathVariable("planetId") String planetId) {
    	try {
    		Planet planet = service.findById(planetId).get();
    	    Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(planet.getPlanetId()).withSelfRel();
    	    return new Resource<Planet>(planet, selfLink);
		} catch (PlanetNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
    }

    @GetMapping("/byname/{planetName}")
    public Resource<Planet> getPlanetByName(@PathVariable("planetName") String planetName) {
    	
        try {
        	Planet planet = service.findByName(planetName).get();
    	    Link selfLink = ControllerLinkBuilder.linkTo(this.getClass()).slash(planet.getPlanetId()).withSelfRel();
    	    return new Resource<Planet>(planet, selfLink);
		} catch (PlanetnameNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
    }

    @DeleteMapping("/{planetId}")
    public HttpStatus deletePlanet(@PathVariable String planetId) {
    	try {
			service.deletePlanet(planetId);
			return HttpStatus.OK;
		} catch (PlanetNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
    }
}








