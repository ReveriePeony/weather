package com.night.weather.service.acg.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.night.weather.common.base.BaseServiceImpl;
import com.night.weather.dao.acg.AcgResourcesDAO;
import com.night.weather.entity.acg.AcgResources;
import com.night.weather.service.acg.AcgResourcesService;
import com.night.weather.spider.DmhySpider;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月21日
 */
@Service
public class AcgResourcesServiceImpl extends BaseServiceImpl<AcgResourcesDAO, AcgResources>
		implements AcgResourcesService {

	@Autowired
	private DmhySpider spider;
	
//	@PostConstruct
	private void spiderStart() {
		spider.start();
	}
	

}
