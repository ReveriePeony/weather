package com.night.weather.service.acg.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.night.weather.common.base.BaseServiceImpl;
import com.night.weather.dao.acg.AcgClassDAO;
import com.night.weather.entity.acg.AcgClass;
import com.night.weather.service.acg.AcgClassService;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月21日
 */
@Service
public class AcgClassServiceImpl extends BaseServiceImpl<AcgClassDAO, AcgClass> implements AcgClassService {

	@Autowired
	private AcgClassDAO dao;

	@Override
	public boolean updateQuantity(AcgClass ac, boolean isSpider) {
		if (isSpider)
			return dao.updateQuantitySpider(ac) != 0 ? true : false;
		else
			return dao.updateQuantity(ac) != 0 ? true : false;
	}

}
