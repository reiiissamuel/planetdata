package com.reiiissamuel.planetdata.planetData.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reiiissamuel.planetdata.planetData.entity.Planet;

@Repository
public interface PlanetRepository extends MongoRepository<Planet, String> {

    //Planet findByStudentNumber(String planetId);

    Optional<Planet> findByName(String planetName);

    //List<Planet> findAllPlanetOrderByName();
}
