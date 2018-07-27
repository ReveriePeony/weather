package com.night.weather.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import com.night.weather.config.intercepter.ErrorInterceptor;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月18日
 */
@Configuration
public class MvcConfig extends WebMvcConfigurationSupport{
	
	@Override
	protected void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new ErrorInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}
	
	@Override
	protected void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowCredentials(true)  
			.allowedHeaders("*")  
			.allowedOrigins("*")  
			.allowedMethods("*");  
		super.addCorsMappings(registry);
	}

	@Override
	protected void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("swagger-ui.html")
			.addResourceLocations("classpath:/META-INF/resources/");
		registry.addResourceHandler("/webjars/**")
			.addResourceLocations("classpath:/META-INF/resources/webjars/");
		registry.addResourceHandler("/**")
		.addResourceLocations("classpath:/static/");
		super.addResourceHandlers(registry);
	}
}
