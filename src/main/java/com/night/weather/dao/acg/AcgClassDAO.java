package com.night.weather.dao.acg;

import org.apache.ibatis.annotations.Update;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.night.weather.entity.acg.AcgClass;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月21日
 */
public interface AcgClassDAO extends BaseMapper<AcgClass> {

	@Update("UPDATE acg_class SET updatequantity = #{updatequantity} WHERE id = #{id}")
	int updateQuantity(AcgClass ac);
	
	@Update("UPDATE acg_class SET updatequantity = #{updatequantity}, updatetime = #{updatetime} WHERE id = #{id}")
	int updateQuantitySpider(AcgClass ac);

}
