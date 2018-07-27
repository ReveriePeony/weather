package com.night.weather.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.night.weather.common.dto.ResponseDTO;
import com.night.weather.entity.County;
import com.night.weather.entity.WeatherResponse;
import com.night.weather.service.CityDataService;
import com.night.weather.service.WeatherDataService;
import com.night.weather.service.acg.AcgClassService;

import lombok.extern.slf4j.Slf4j;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月21日
 */
@Slf4j
@RestController
@RequestMapping("weather")
public class WeatherController {

	@Autowired
	private WeatherDataService weatherDataService;

	@Autowired
	private CityDataService cityDataService;

	@Autowired
	private AcgClassService acgClassService;

	// @RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/index.html");
		return modelAndView;
	}

	@PostMapping("/getDataByCityId/{cityId}")
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
		return new ModelAndView("weather/index2", "model", model);
	}

	@GetMapping("/getCityList")
	public ResponseDTO<List<County>> getCityList() {
		try {
			return ResponseDTO.createSuccess(cityDataService.getCityList());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseDTO.createErrorMsg();
	}

	
}
