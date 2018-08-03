package cn.dw.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.dw.model.Product;
import cn.dw.service.ProductService;

// 此Controller取代了Servlet但是需要继承任何父类
// @WebServlet
@RequestMapping("/category")
public class CategoryController extends BaseController {
	// 1: ajax也是http请求,但是返回的是数据不是页面
	@RequestMapping("/ajax")
	@ResponseBody  // 返回不是页面,而是内容
	public Object ajax(String name,String pass) {
		System.out.println(name + "," + pass);
		// 模拟获取数据库请求,并且返回JSON格式
		List<Product> proList = new ArrayList<Product>();
		Product p1 = new Product();
		p1.setName("手机");
		p1.setId(1);
		proList.add(p1);
		Product p2 = new Product();
		p2.setName("电脑");
		p2.setId(2);
		proList.add(p2);
		// json就是有格式的字符串
		Map<String, Object> hash = new HashMap<String,Object>();
		hash.put("title", "我是标题");
		hash.put("proList", proList);
		return hash;
	}

}
