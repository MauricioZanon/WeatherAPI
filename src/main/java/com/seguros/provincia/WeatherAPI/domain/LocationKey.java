package com.seguros.provincia.WeatherAPI.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public class LocationKey {
	@JsonProperty(value = "Key")
	public String claveCiudad;
}
