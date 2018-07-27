package com.night.weather.job;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.night.weather.spider.DmhySpider;

/**
 * 启动后执行
 * @author Reverien9@gmail.com
 * @date 2018年7月23日
 */
@Component
public class ResourceRunner implements ApplicationRunner{

	@Autowired
	private DmhySpider spider;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		spider.start();
	}

}
