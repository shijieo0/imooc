package com.waylau.spring.cloud.weather.service;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.waylau.spring.cloud.weather.vo.WeatherResponse;

/**
 * Weather Data Client.
 * 
 * @since 1.0.0 2017年11月28日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@FeignClient("msa-weather-data-eureka")
public interface WeatherDataClient {
	
	@GetMapping("/weather/cityId/{cityId}")
	WeatherResponse getDataByCityId(@PathVariable("cityId") String cityId);
}
