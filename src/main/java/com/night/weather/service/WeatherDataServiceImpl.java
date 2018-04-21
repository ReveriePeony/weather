package com.night.weather.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.night.weather.entity.WeatherResponse;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";

	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public WeatherResponse getDataById(String cityId) {
		String uri = WEATHER_URI + "cityKey=" + cityId;
		return getWeather(uri);
	}

	@Override
	public WeatherResponse getDataByName(String cityName) {
		String uri = WEATHER_URI + "city=" + cityName;
		return getWeather(uri);
	}
	
	private WeatherResponse getWeather(String uri) {
		WeatherResponse weatherResponse = null;
		ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
		String body = null;
		if(entity.getStatusCodeValue() == 200) {
			body = entity.getBody();
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			weatherResponse = mapper.readValue(body, WeatherResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return weatherResponse;
	}

}
