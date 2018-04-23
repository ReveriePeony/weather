package com.night.weather.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * 配置文件
 * @author Reverien9@gmail.com
 * @date 2018年4月22日
 */
@Component
@ConfigurationProperties(prefix = "weather")
@PropertySource(value = {"classpath:/application-weather.yml"})
@Data
public class WeatherConfig {

	private String uri;
	
	private Long redisTimeOut;
	
	private Integer jobTime;
	
}
