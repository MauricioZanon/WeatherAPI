package com.seguros.provincia.WeatherAPI.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonAlias;

@Entity
public class CurrentCondition {
	@Id
	public String nombreCiudad;
	@JsonAlias(value = "LocalObservationDateTime")
	public String fechaYHora;
	@JsonAlias(value = "EpochTime")
	public String tiempoUnix;
	@JsonAlias(value = "WeatherText")
	public String clima;
	@JsonAlias(value = "WeatherIcon")
	public int icono;
	@JsonAlias(value = "HasPrecipitation")
	public boolean estaLloviendo;
	@JsonAlias(value = "PrecipitationType")
	public String tipoDePrecipitacion;
	@JsonAlias(value = "IsDayTime")
	public boolean esDeDia;
	@JsonAlias(value = "MobileLink")
	public String linkMovil;
	@JsonAlias(value = "Link")
	public String link;

}
