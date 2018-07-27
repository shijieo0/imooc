package com.waylau.spring.cloud.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.waylau.spring.cloud.weather.vo.Weather;
import com.waylau.spring.cloud.weather.vo.WeatherResponse;

/**
 * Weather Report Service.
 * 
 * @since 1.0.0 2017年11月24日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Service
public class WeatherReportServiceImpl implements WeatherReportService {
	@Autowired
	private WeatherDataService  weatherDataService;
	
	@Override
	public Weather getDataByCityId(String cityId) {
		WeatherResponse resp = weatherDataService.getDataByCityId(cityId);
		return resp.getData();
	}

}
