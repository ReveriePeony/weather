package com.night.weather.entity.acg;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月21日
 */
@Data
@EqualsAndHashCode(callSuper=false)
@TableName("acg_class")
public class AcgClass extends Model<AcgClass> {

	private static final long serialVersionUID = 4131509992310282137L;

	private Long id;
	
	private String classname;
	
	private String classurl;

	private String classimg;
	
	private Integer status = 1;

	private Integer updatequantity;
	
	private String createtime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	
	private Long millisecond = System.currentTimeMillis();

	private Long updatetime;

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	
}
