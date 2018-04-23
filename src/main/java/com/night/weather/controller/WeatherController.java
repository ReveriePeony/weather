package com.night.weather.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.night.weather.entity.WeatherResponse;
import com.night.weather.service.CityDataService;
import com.night.weather.service.WeatherDataService;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
@RestController
@RequestMapping("weather")
public class WeatherController {
	
	private static final Logger log = LoggerFactory.getLogger(WeatherController.class);
	
	@Autowired
	private WeatherDataService weatherDataService;

	@Autowired
	private CityDataService cityDataService;
	
//	@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public String index() {
		return "hello world";
	}
	
	@GetMapping("/getDataByCityId/{cityId}")
	public WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId) {
		return weatherDataService.getDataById(cityId);
	}
	
	@GetMapping("/getDataByCityName/{cityName}")
	public WeatherResponse getDataByCityName(@PathVariable("cityName") String cityName) {
		return weatherDataService.getDataByName(cityName);
	}
	
	@GetMapping("/viewByCityId/{cityId}")
	public ModelAndView getViewByCityId(@PathVariable("cityId") String cityId, Model model) {
		model.addAttribute("title", "天气预报首页");
		model.addAttribute("cityId", cityId);
		try {
			model.addAttribute("cityList", cityDataService.getCityList());
		} catch (Exception e) {
			log.error("getViewByCityId cityDataService.getCityList() error !", e);
		}
		model.addAttribute("data", weatherDataService.getDataById(cityId).getData());
		return new ModelAndView("weather/index", "model", model);
	}
	
}
