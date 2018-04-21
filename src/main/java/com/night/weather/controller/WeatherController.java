package com.night.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.night.weather.entity.WeatherResponse;
import com.night.weather.service.WeatherDataService;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
@RestController
@RequestMapping("weather")
public class WeatherController {
	
	@Autowired
	private WeatherDataService weatherDataService;
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String index() {
		return "hello world";
	}
	
	@GetMapping("/getDataByCityId/{cityId}")
	public WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId) {
		return weatherDataService.getDataById(cityId);
	}
	
	@GetMapping("/getDataByCityName/{cityName}")
	public WeatherResponse getDataByCityName(@PathVariable("cityName") String cityName) {
		return weatherDataService.getDataByName(cityName);
	}
	
	
}
