package com.night.weather.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.night.weather.entity.County;
import com.night.weather.service.CityDataService;
import com.night.weather.service.WeatherDataService;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月22日
 */
public class WeatherDataSyncJob extends QuartzJobBean{
	
	private final static Logger log = LoggerFactory.getLogger(WeatherDataSyncJob.class);

	@Autowired
	private CityDataService cityDataService;

	@Autowired
	private WeatherDataService weatherDataService;
	
	@Override
	protected void executeInternal(JobExecutionContext arg0) throws JobExecutionException {
		log.info("weather sync data job");
		List<County> counties = null;
		try {
			counties = cityDataService.getCityList();
		} catch (Exception e) {
			log.error("cityDataService.getCityList() error!", e);
		}
		for(County c : counties) {
			weatherDataService.SyncDataById(c.getWeatherCode());
		}
	}

}
