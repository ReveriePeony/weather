package com.night.weather.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月22日
 */
public class ScheduledConfig implements SchedulingConfigurer {
	@Override
	public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
		scheduledTaskRegistrar.setScheduler(setTaskExecutors());
	}

	@Bean(destroyMethod = "shutdown")
	public Executor setTaskExecutors() {
		return Executors.newScheduledThreadPool(4);
	}
}