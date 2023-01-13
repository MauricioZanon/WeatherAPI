package com.seguros.provincia.WeatherAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.seguros.provincia.WeatherAPI.domain.CurrentCondition;
import com.seguros.provincia.WeatherAPI.service.CurrentConditionService;

@Validated
@RestController
public class CurrentConditionController {

	@Autowired
	private CurrentConditionService weatherService;

	/**
	 * Unico endpoint de la API, recibe el nombre de la ciudad de la cual se quiere
	 * obtener la información y devuelve un error en caso de recibir un string vacío
	 * 
	 * @param nombreCiudad El nombre de la ciudad de la cual se buscarán las
	 *                     condiciones climáticas
	 * @return Devuelve un objeto JSON en el response con la información
	 */
	@GetMapping("/condicionActual")
	public CurrentCondition obtener(@RequestParam(name = "nombreCiudad") String nombreCiudad) {
		if (nombreCiudad.equals("")) {
			throw new IllegalArgumentException("El parámetro 'nombreCiudad' es obligatorio.");
		}

		return weatherService.obtener(nombreCiudad);
	}

	/**
	 * Handler para manejar las IllegalArgumentExceptions
	 * 
	 * @param e La excepción lanzada
	 * @return El mensaje de la excepción
	 */
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public String exceptionHandlerIllegalArgumentException(IllegalArgumentException e) {
		return e.getMessage();
	}

}
