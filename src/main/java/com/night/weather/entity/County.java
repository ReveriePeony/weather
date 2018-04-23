package com.night.weather.entity;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

import lombok.Data;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月23日
 */
@Data
@XmlRootElement(name = "county")
@XmlAccessorType(XmlAccessType.FIELD)
public class County implements Serializable{
	
	private static final long serialVersionUID = 5129237967705743959L;

	@XmlAttribute(name = "id")
	private String id;
	
	@XmlAttribute(name = "name")
	private String name;
	
	@XmlAttribute(name = "weatherCode")
	private String weatherCode;
}
