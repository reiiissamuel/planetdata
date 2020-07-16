package com.reiiissamuel.planetdata.planetData.entity;

import java.util.Objects;

import javax.validation.constraints.NotBlank;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.validation.annotation.Validated;



/**
 * Planet
 */
@Validated
@Document(collection = "Planets")
public class Planet {
	
	
  @Id
  //@JsonIgnore
  private String planetId;

  @NotBlank(message = "O campo Name é obrigatótio")
  private String name;

  @NotBlank(message = "O campo Climate é obrigatótio")
  private String climate;

  @NotBlank(message = "O campo Terrain é obrigatótio")
  private String terrain;

 // @JsonIgnore
  private int filmAppearancesqtt;
  
  
  



public String getPlanetId() {
    return planetId;
  }

  public void setId(String planetId) {
    this.planetId = planetId;
  }

  public Planet name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  **/


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getClimate() {
    return climate;
  }

  public void setClimate(String climate) {
    this.climate = climate;
  }

  public String getTerrain() {
    return terrain;
  }

  public void setTerrain(String terrain) {
    this.terrain = terrain;
  }

  public int getFilmAppearancesqtt() {
    return filmAppearancesqtt;
  }

  public void setFilmAppearancesqtt(int filmAppearancesqtt) {
    this.filmAppearancesqtt = filmAppearancesqtt;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Planet planet = (Planet) o;
    return Objects.equals(this.planetId, planet.planetId) &&
        Objects.equals(this.name, planet.name) &&
        Objects.equals(this.climate, planet.climate) &&
        Objects.equals(this.terrain, planet.terrain) &&
        Objects.equals(this.filmAppearancesqtt, planet.filmAppearancesqtt);
  }

  @Override
  public int hashCode() {
    return Objects.hash(planetId, name, climate, terrain, filmAppearancesqtt);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Planet {\n");
    
    sb.append("    id: ").append(toIndentedString(planetId)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    climate: ").append(toIndentedString(climate)).append("\n");
    sb.append("    terrain: ").append(toIndentedString(terrain)).append("\n");
    sb.append("    filmAppearancesqtt: ").append(toIndentedString(filmAppearancesqtt)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

