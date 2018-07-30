package com.night.weather.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.night.weather.spider.DmhySpider;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月22日
 */
@Slf4j
@Component
public class ScheduledJob {
	
	@Autowired
	private DmhySpider spider;

	@Scheduled(cron = "0 0 * * * ?")
	public void spiderJob() {
		log.info("start dmhy spider");
		spider.start();
	}

}
