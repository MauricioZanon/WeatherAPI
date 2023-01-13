package com.seguros.provincia.WeatherAPI.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.seguros.provincia.WeatherAPI.domain.CurrentCondition;
import com.seguros.provincia.WeatherAPI.domain.LocationKey;
import com.seguros.provincia.WeatherAPI.repository.CurrentConditionRepository;

@Service
public class CurrentConditionService {

	private final CurrentConditionRepository currentConditionRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Value("${weather.api.key}")
	private String apiKey;
	@Value("${weather.api.currentconditionurl}")
	private String condicionActualUrl;
	@Value("${weather.api.citysearchurl}")
	private String citySearchUrl;

	public CurrentConditionService(CurrentConditionRepository currentConditionRepository) {
		this.currentConditionRepository = currentConditionRepository;
	}

	/**
	 * Busca las condicionees climáticas en la ciudad otorgada y la guarda en la
	 * base de datos, previo a consultar a AccuWeather
	 * el servicio se asegurará de no contar con la información en la base, en caso
	 * de tenerla omitirá la consulta.
	 * 
	 * @param nombreCiudad El nombre de la ciudad de la cual se buscarán las
	 *                     condiciones climáticas
	 * @return Un objeto con información del clima en la ciudad recibida
	 */
	public CurrentCondition obtener(String nombreCiudad) {
		Optional<CurrentCondition> currentConditionOpt = currentConditionRepository.findById(nombreCiudad);
		if (currentConditionOpt.isPresent()) {
			return currentConditionOpt.get();
		} else {
			String claveCiudad = obtenerClaveCiudad(nombreCiudad);
			return obtenerCurrentCondition(claveCiudad, nombreCiudad);
		}
	}

	/**
	 * Envía una consulta a AccuWeather con el nombre de la ciudad requerida para
	 * obtener la clave de la misma.
	 * 
	 * @param nombreCiudad El nombre de la ciudad de la que se quiere la clave
	 * @return Un objeto conteniendo la clave de la ciudad.
	 */
	private String obtenerClaveCiudad(String nombreCiudad) {
		String url = citySearchUrl + "?apikey=" + apiKey + "&q=" + nombreCiudad;
		LocationKey locationKey = restTemplate.getForObject(url, LocationKey[].class)[0];
		return locationKey.claveCiudad;
	}

	/**
	 * Envía una consulta a AccuWeather con la clave de la ciudad de la cual se
	 * quiere obtener información
	 * 
	 * @param claveCiudad  La clave de la ciudad de la cual se quiere obtener
	 *                     información
	 * @param nombreCiudad El nombre de la ciudad de la cual se quiere obtener
	 *                     información
	 * @return Un objeto con información del clima en la ciudad recibida
	 */
	private CurrentCondition obtenerCurrentCondition(String claveCiudad, String nombreCiudad) {
		String url = condicionActualUrl + claveCiudad + "?apikey=" + apiKey;
		CurrentCondition currentCondition = restTemplate.getForObject(url, CurrentCondition[].class)[0];
		currentCondition.nombreCiudad = nombreCiudad;
		return currentConditionRepository.save(currentCondition);
	}

}
