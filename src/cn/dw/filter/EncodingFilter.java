package cn.dw.filter;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
// 此类是一个过滤器,主要用来过滤请求(自己配置过滤的URL地址)
@WebFilter(urlPatterns="/*")
public class EncodingFilter implements Filter {
	
	public EncodingFilter() {
		System.out.println("EncodingFilter().........");
	}

	@Override
	public void destroy() {
	}

	@Override  // 只要符合条件的请求,都会进入到此方法
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("EncodingFilter doFilter..........");
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 过滤完毕之后如果有下一个过滤链则跳转,否则跳转到目标页面
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
