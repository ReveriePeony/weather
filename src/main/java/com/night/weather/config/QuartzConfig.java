package com.night.weather.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.night.weather.job.WeatherDataSyncJob;

/**
 * 定时任务 配置
 * @author Reverien9@gmail.com
 * @date 2018年4月22日
 */
@Configuration
public class QuartzConfig {

	@Autowired
	private WeatherConfig config;

	@Bean
	public JobDetail weatherJobDetail() {
		return JobBuilder.newJob(WeatherDataSyncJob.class).withIdentity("weatherDataSyncJob").storeDurably().build();
	}

	@Bean
	public Trigger weatherJobTrigger() {
		SimpleScheduleBuilder scheBuilder = SimpleScheduleBuilder.simpleSchedule()
				.withIntervalInSeconds(config.getJobTime()).repeatForever();
		return TriggerBuilder.newTrigger().forJob(weatherJobDetail()).withIdentity("weatherJobTrigger")
				.withSchedule(scheBuilder).build();
	}
}
