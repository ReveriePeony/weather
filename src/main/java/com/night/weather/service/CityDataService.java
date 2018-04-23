package com.night.weather.service;

import java.util.List;

import com.night.weather.entity.County;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月23日
 */
public interface CityDataService {

	/**
	 * 获取城市列表
	 * @return
	 * @throws Exception
	 */
	List<County> getCityList() throws Exception;
	
}