package com.night.weather.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.plugins.PaginationInterceptor;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月21日
 */
@Configuration
@MapperScan("com.night.weather.dao*")
public class MybatisPlusConfig {

	@Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }
	
}
