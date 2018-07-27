package org.imooc.controller.content;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/comments")
public class CommentsController {
	@RequestMapping
	public String init() {
		return "/content/commentList";
	}
}
