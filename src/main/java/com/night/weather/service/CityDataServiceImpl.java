package com.night.weather.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.night.weather.entity.County;
import com.night.weather.entity.Province;
import com.night.weather.util.XmlBuilder;

/**
 * 
 * @author Reverien9@gmail.com
 * @date 2018年4月23日
 */
@Service
public class CityDataServiceImpl implements CityDataService {

	@Override
	public List<County> getCityList() throws Exception {
		// 读取xml数据
		Resource resource = new ClassPathResource("citylist.xml");
		BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream(), "UTF-8"));
		StringBuffer sb = new StringBuffer();
		String line = null;
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		Province province = (Province) XmlBuilder.xmlStr2Object(Province.class, sb.toString());
		List<County> counytList = new ArrayList<>();
		province.getCityList().forEach(city -> counytList.addAll(city.getCountyList()));
		return counytList;
	}

}
