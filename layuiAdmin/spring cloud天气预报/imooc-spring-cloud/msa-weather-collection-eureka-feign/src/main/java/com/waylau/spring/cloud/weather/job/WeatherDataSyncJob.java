package com.waylau.spring.cloud.weather.job;

import java.util.ArrayList;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.waylau.spring.cloud.weather.service.CityClient;
import com.waylau.spring.cloud.weather.service.WeatherDataCollectionService;
import com.waylau.spring.cloud.weather.vo.City;

/**
 * Weather Data Sync Job.
 * 
 * @since 1.0.0 2017年11月23日
 * @author <a href="https://waylau.com">Way Lau</a> 
 */
public class WeatherDataSyncJob extends QuartzJobBean {
	
	private final static Logger logger = LoggerFactory.getLogger(WeatherDataSyncJob.class);  

	@Autowired
	private WeatherDataCollectionService weatherDataCollectionService;

	@Autowired
	private CityClient cityClient;
	
	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		logger.info("Weather Data Sync Job. Start！");
		// 获取城市ID列表
		List<City> cityList = null;
		
		try {
			
			// 由城市数据API微服务提供数据
			cityList = cityClient.listCity();
		} catch (Exception e) {
			logger.error("Exception!", e);
		}
		
		// 遍历城市ID获取天气
		for (City city : cityList) {
			String cityId = city.getCityId();
			logger.info("Weather Data Sync Job, cityId:" + cityId);
			
			weatherDataCollectionService.syncDateByCityId(cityId);
		}
		
		logger.info("Weather Data Sync Job. End！");
	}

}
