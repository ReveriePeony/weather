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
@TableName("acg_resources")
public class AcgResources extends Model<AcgResources> {

	private static final long serialVersionUID = 4131509992310282138L;

	private Long id;
	
	private Long classid;

	private String name;
	
	private String torrent;
	
	private String magnet;
	
	private String publisher;

	private String size;
	
	private String resourcestime;
	
	private Long resourcesmillis;
	
	private String createtime = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(LocalDateTime.now());
	
	private Long millisecond = System.currentTimeMillis();

	@Override
	protected Serializable pkVal() {
		return this.id;
	}
	
}
