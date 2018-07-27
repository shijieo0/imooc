package com.waylau.spring.cloud.weather.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.waylau.spring.cloud.weather.vo.WeatherResponse;

/**
 * WeatherDataService 实现.
 * 
 * @since 1.0.0 2017年11月22日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
@Service
public class WeatherDataServiceImpl implements WeatherDataService {
	private static final String WEATHER_URI = "http://wthrcdn.etouch.cn/weather_mini?";
	
	@Autowired
	private RestTemplate restTemplate;
	
	@Override
	public WeatherResponse getDataByCityId(String cityId) {
		String uri = WEATHER_URI + "citykey=" + cityId;
		return this.doGetWeahter(uri);
	}

	@Override
	public WeatherResponse getDataByCityName(String cityName) {
		String uri = WEATHER_URI + "city=" + cityName;
		return this.doGetWeahter(uri);
	}
	
	private WeatherResponse doGetWeahter(String uri) {
 		ResponseEntity<String> respString = restTemplate.getForEntity(uri, String.class);
		
		ObjectMapper mapper = new ObjectMapper();
		WeatherResponse resp = null;
		String strBody = null;
		
		if (respString.getStatusCodeValue() == 200) {
			strBody = respString.getBody();
		}

		try {
			resp = mapper.readValue(strBody, WeatherResponse.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return resp;
	}

}
