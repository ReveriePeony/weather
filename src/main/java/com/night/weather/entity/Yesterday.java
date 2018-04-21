package com.night.weather.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 昨天天气
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
@Data
public class Yesterday implements Serializable {

	private static final long serialVersionUID = -2438952625153961436L;
	private String date;
    private String high;
    private String fx;
    private String low;
    private String fl;
    private String type;

}