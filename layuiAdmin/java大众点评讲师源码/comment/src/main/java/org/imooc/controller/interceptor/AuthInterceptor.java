package org.imooc.controller.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.imooc.util.CommonUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class AuthInterceptor implements HandlerInterceptor {

	/**
	 * 在进入Handler方法执行之前执行本方法
	 * 
	 * @return true:执行下一个拦截器，直到所有拦截器都执行完，再执行被拦截的Controller
	 *         false:从当前的拦截器往回执行所有拦截器的afterCompletion(),再退出拦截器链
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if (CommonUtil.contains(request.getSession(), request.getServletPath(), request.getMethod())) {
			return true;
		}
		// 针对ajax请求处理
		if (request.getHeader("x-requested-with") != null) {
			String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
			response.setHeader("url", basePath + "/login/noAuth");
		} else {
			request.getRequestDispatcher("/login/noAuth").forward(request, response);
		}
		return false;
	}

	/**
	 * 在进入Handler方法之后，返回ModelAndView之前执行
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 在Handler方法执行完之后执行
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)throws Exception {
		// TODO Auto-generated method stub

	}

}