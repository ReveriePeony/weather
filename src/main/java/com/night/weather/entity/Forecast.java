package com.night.weather.entity;

import java.io.Serializable;

import lombok.Data;

/**
 * 预测
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
@Data
public class Forecast implements Serializable{

	private static final long serialVersionUID = -4662805531151992087L;
	private String date;
    private String high;
    private String fengli;
    private String low;
    private String fengxiang;
    private String type;

}