package com.night.weather.service;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.night.weather.config.WeatherConfig;
import com.night.weather.entity.WeatherResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
@Slf4j
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	
	@Autowired
	private WeatherConfig weatherConfig;

	@Autowired
	private RestTemplate restTemplate;
	
	@Autowired
	private StringRedisTemplate redisTemplate;
	
	@Override
	public WeatherResponse getDataById(String cityId) {
		String uri = weatherConfig.getUri() + "citykey=" + cityId;
		return this.getWeather(uri);
	}

	@Override
	public WeatherResponse getDataByName(String cityName) {
		String uri = weatherConfig.getUri() + "city=" + cityName;
		return this.getWeather(uri);
	}
	
	private WeatherResponse getWeather(String uri) {
		WeatherResponse weatherResponse = null;
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		String body = null;
		if(redisTemplate.hasKey(uri)) {
			log.info("redis has data");
			body = opsForValue.get(uri);
		}else {
			log.info("redis don't has data");
			ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
			if(entity.getStatusCodeValue() == 200) {
				body = entity.getBody();
			}
			opsForValue.set(uri, body, weatherConfig.getRedisTimeOut(), TimeUnit.SECONDS);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			weatherResponse = mapper.readValue(body, WeatherResponse.class);
		} catch (IOException e) {
			log.error("mapper.readValue(body, WeatherResponse.class) error !", e);
		}
		
		return weatherResponse;
	}

	/**
	 * 根据城市ID同步天气数据
	 */
	@Override
	public void SyncDataById(String cityId) {
		String uri = weatherConfig.getUri() + "cityKey=" + cityId;
		this.saveWeatehrData(uri);
	}
	
	private void saveWeatehrData(String uri) {
		ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
		String body = null;
		ResponseEntity<String> entity = restTemplate.getForEntity(uri, String.class);
		if(entity.getStatusCodeValue() == 200) {
			body = entity.getBody();
		}
		opsForValue.set(uri, body, weatherConfig.getRedisTimeOut(), TimeUnit.SECONDS);
	}

}
