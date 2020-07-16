package com.reiiissamuel.planetdata.planetData.service;

import java.util.List;
import java.util.Optional;

import com.reiiissamuel.planetdata.planetData.entity.Planet;
import com.reiiissamuel.planetdata.planetData.exceptions.NotAStarWarsPlanetException;
import com.reiiissamuel.planetdata.planetData.exceptions.PlanetExistsException;
import com.reiiissamuel.planetdata.planetData.exceptions.PlanetNotFoundException;
import com.reiiissamuel.planetdata.planetData.exceptions.PlanetnameNotFoundException;

public interface PlanetServiceSpecification {
	
	List<Planet> findAll();

    Optional<Planet> findByName(String planetName) throws  PlanetnameNotFoundException;

    Planet addPlanet(Planet planet) throws PlanetExistsException, NotAStarWarsPlanetException;
    
    void deletePlanet(String planetId) throws PlanetNotFoundException;

	Optional<Planet> findById(String planetId) throws PlanetNotFoundException;
}