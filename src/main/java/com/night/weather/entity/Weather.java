package com.night.weather.entity;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

/**
 * 天气类
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
@Data
public class Weather implements Serializable{

	private static final long serialVersionUID = 5593092786615759843L;
	
	private String city;
	private String aqi;
	private String ganmao;
	private String wendu;
	private Yesterday yesterday;
	private List<Forecast> forecast;

}