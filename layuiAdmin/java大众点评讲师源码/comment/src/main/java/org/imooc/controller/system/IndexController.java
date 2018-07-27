package org.imooc.controller.system;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class IndexController {

	/**
	 * 登录成功后，后台管理首页
	 */
	@RequestMapping
	public String init() {
		return "/system/index";
	}
}
