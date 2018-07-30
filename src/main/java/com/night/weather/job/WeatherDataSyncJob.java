package com.night.weather.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.night.weather.entity.County;
import com.night.weather.service.CityDataService;
import com.night.weather.service.WeatherDataService;

import lombok.extern.slf4j.Slf4j;

/**
 * 天气数据同步任务
 * @author Reverien9@gmail.com
 * @date 2018年4月22日
 */
@Slf4j
public class WeatherDataSyncJob extends QuartzJobBean{
	
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
		counties.forEach(c -> weatherDataService.SyncDataById(c.getWeatherCode()));
	}

}
