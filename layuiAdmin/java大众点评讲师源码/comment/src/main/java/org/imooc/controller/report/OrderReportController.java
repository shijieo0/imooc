package org.imooc.controller.report;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import org.imooc.dto.echarts.Option;
import org.imooc.dto.echarts.Serie;
import org.imooc.service.OrderReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/orderReport")
public class OrderReportController {
	
	@Autowired
	private OrderReportService orderReportService;
	
	@RequestMapping
	public String index() {
		return "/report/orderCount";
	}
	
	@ResponseBody
	@RequestMapping(value="/count" , method = RequestMethod.GET)
	public Option count() {
		Option option = orderReportService.count();
		
		// TODO 演示用数据 -- start
		String[] names = new String[]{"电影","结婚","美食"};
		option.getLegend().setData(Arrays.asList(names));
		Random rand = new Random();
		List<Serie> series = new ArrayList<>();
		for(String name : names) {
			Serie serie = new Serie();
			series.add(serie);
			serie.setName(name);
			serie.setType("line");
			for(int i = 0; i < 24; i++) {
				serie.getData().add(Long.valueOf(rand.nextInt(1000)));
			}
		}
		option.setSeries(series);
		// TODO 演示用数据 -- end
		
		return option;
	}
}