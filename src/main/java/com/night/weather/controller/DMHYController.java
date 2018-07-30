package com.night.weather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.night.weather.common.dto.ResponseDTO;
import com.night.weather.entity.acg.AcgClass;
import com.night.weather.entity.acg.AcgResources;
import com.night.weather.service.acg.AcgClassService;
import com.night.weather.service.acg.AcgResourcesService;
import com.night.weather.spider.DmhySpider;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年6月25日
 */
@RestController
@RequestMapping("dmhy")
public class DMHYController {

	@Autowired
	private AcgClassService acgClassService;

	@Autowired
	private AcgResourcesService acgResourcesService;

	@Autowired
	private DmhySpider spider;

	@SuppressWarnings("rawtypes")
	@PostMapping("/addClass")
	public ResponseDTO addclass(@RequestBody AcgClass acgClass) {
		acgClassService.insert(acgClass);
		return ResponseDTO.createSuccessMsg();
	}
	
	@SuppressWarnings("rawtypes")
	@PostMapping("/editClass")
	public ResponseDTO editclass(@RequestBody AcgClass acgClass) {
		acgClassService.updateById(acgClass);
		return ResponseDTO.createSuccessMsg();
	}

	@SuppressWarnings("rawtypes")
	@GetMapping("/spider")
	public ResponseDTO spider() {
		try {
			spider.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseDTO.createSuccessMsg();
	}

	@PostMapping("/class")
	public ResponseDTO<Page<AcgClass>> getClass(@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "20") Integer pageSize, 
			@RequestParam() String keyword) {
		Page<AcgClass> result = new Page<>(page, pageSize, "updatetime", false);
		Wrapper<AcgClass> w = new EntityWrapper<>();
		w.like("week", keyword).or().like("classname", keyword);
		acgClassService.selectPage(result, w);
		return ResponseDTO.createSuccess(result);
	}

	@PostMapping("/resources/{classid}")
	public ResponseDTO<Page<AcgResources>> getResources(@PathVariable("classid") Long classid,
			@RequestParam(value = "page", defaultValue = "1") Integer page,
			@RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize, String keyword) {
		Page<AcgResources> result = new Page<>(page, pageSize, "resourcesmillis", false);
		Wrapper<AcgResources> wrapper = new EntityWrapper<AcgResources>();
		wrapper.eq("classid", classid);
		if (!StringUtils.isEmpty(keyword))
			wrapper.andNew().like("name", keyword).or().like("publisher", keyword).or().like("resourcestime", keyword);
		acgResourcesService.selectPage(result, wrapper);
		return ResponseDTO.createSuccess(result);
	}

	@PostMapping("/toList/{id}")
	public ResponseDTO<?> toList(@PathVariable("id") Long id) {
		AcgClass ac = new AcgClass();
		ac.setId(id);
		ac.setUpdatequantity(null);
		if (acgClassService.updateQuantity(ac, false))
			return ResponseDTO.createSuccessMsg();
		else
			return ResponseDTO.createErrorMsg();
	}

}