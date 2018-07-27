package com.night.weather.service.acg;

import com.night.weather.common.base.BaseService;
import com.night.weather.entity.acg.AcgClass;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月21日
 */
public interface AcgClassService extends BaseService<AcgClass> {

	/**
	 * 更新 quantity
	 * @param ac
	 * @return
	 */
	boolean updateQuantity(AcgClass ac, boolean isSpider);


}
