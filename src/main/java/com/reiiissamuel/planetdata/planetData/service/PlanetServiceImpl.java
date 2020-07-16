package com.reiiissamuel.planetdata.planetData.service;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.reiiissamuel.planetdata.planetData.entity.Planet;
import com.reiiissamuel.planetdata.planetData.exceptions.PlanetExistsException;
import com.reiiissamuel.planetdata.planetData.exceptions.PlanetNotFoundException;
import com.reiiissamuel.planetdata.planetData.exceptions.PlanetnameNotFoundException;
import com.reiiissamuel.planetdata.planetData.repositories.PlanetRepository;

@Service
public class PlanetServiceImpl implements PlanetServiceSpecification{
	
	private static final Logger log = LoggerFactory.getLogger(PlanetServiceImpl.class);
	
	@Autowired
	private PlanetRepository planetRepository;

	@Override
	public List<Planet> findAll() {
		return planetRepository.findAll();
	}

	@Override
	public Optional<Planet> findByName(String planetName) throws PlanetnameNotFoundException {
		Optional<Planet> optionalPlanet = planetRepository.findByName(planetName);
		
		if(!optionalPlanet.isPresent()) {
			throw new PlanetnameNotFoundException("Não existe um planeta com o nome especifcado!");
		}
		
		return optionalPlanet;
	}

	@Override
	public Optional<Planet> findById(String planetId) throws PlanetNotFoundException {
		Optional<Planet> optionalPlanet = planetRepository.findById(planetId);
		
		if(!optionalPlanet.isPresent()) {
			throw new PlanetNotFoundException("Não existe um planeta com o id especifcado!");
		}
		
		return optionalPlanet;
	}

	@Override
	public Planet addPlanet(Planet planet) throws PlanetExistsException{
		Optional<Planet> existingPlanet = planetRepository.findByName(planet.getName());
		
		if(existingPlanet.isPresent()) {
			throw new PlanetExistsException("Este planeta ja existe no repositório!");
		}
		
		log.info("Planet Json: '{}'", planet.toString());
		return planetRepository.save(planet);
	}

	@Override
	public void deletePlanet(String planetId) throws PlanetNotFoundException {
		Optional<Planet> optionalPlanet = planetRepository.findById(planetId);
		
		if(!optionalPlanet.isPresent()) {
			throw new PlanetNotFoundException("Não existe um planeta com o id especifcado!");
		}
		
		
		planetRepository.deleteById(planetId);
		
	}

}
