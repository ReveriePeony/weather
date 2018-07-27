package com.night.weather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月18日
 */
@RestController
@RequestMapping("/")
public class Controller {
	
	@GetMapping("/")
	public ModelAndView index() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("redirect:/index.html");
		return modelAndView;
	}
	
}
