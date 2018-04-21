package com.night.weather.service;

import com.night.weather.entity.WeatherResponse;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
public interface WeatherDataService {
	
	/**
	 * 根据城市ID查询天气数据
	 * @param cityId
	 * @return
	 */
	WeatherResponse getDataById(String cityId);
	
	/**
	 * 根据城市名查询天气数据
	 * @param cityName
	 * @return
	 */
	WeatherResponse getDataByName(String cityName);
	
}
