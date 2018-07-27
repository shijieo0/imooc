package org.imooc.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/orders")
public class OrdersController {
	
	@RequestMapping
	public String init() {
		return "/content/orderList";
	}
}