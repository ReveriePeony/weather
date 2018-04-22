package com.night.weather.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
@Configuration
public class WeatherConfiguration {

	@Autowired
	private RestTemplateBuilder builder;

	@Bean
	public RestTemplate restTemplate() {
		SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
		factory.setReadTimeout(5000);// ms
		factory.setConnectTimeout(15000);// ms
		return builder.configure(new RestTemplate(factory));
	}

}
