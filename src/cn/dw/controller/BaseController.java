package cn.dw.controller;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import cn.dw.service.ProductService;

public class BaseController {
	
	// spring-mvc.xml配置文件中,需要配置 <property name="productService" ref="ps" />
		protected ProductService productService = null;

		public void setProductService(ProductService productService) {
			System.out.println("setProductService.......");
			this.productService = productService;
		}

		// 只需要指定注解,就可以按类型注入
		@Resource
		protected HttpServletRequest request;
		@Resource
		protected HttpSession session;
		@Resource
		protected ServletContext application;

}
