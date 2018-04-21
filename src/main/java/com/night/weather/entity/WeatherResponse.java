package com.night.weather.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
@Data
public class WeatherResponse implements Serializable{

	private static final long serialVersionUID = 3590712852610416733L;
	
	private Weather data;
	private Integer status;
	private String desc;

}
