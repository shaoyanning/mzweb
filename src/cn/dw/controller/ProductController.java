package cn.dw.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;

import cn.dw.model.Product;
import cn.dw.service.ProductService;

// 此Controller取代了Servlet但是需要继承任何父类
// @WebServlet
@RequestMapping("/product")
public class ProductController extends BaseController {

	// servlet有doGet doPost,现在可以自定义方法
	// 1: 获取数据,已经有mvc框架搞定
	@RequestMapping("/save")
	public String save(Product product) {
		// 1: 获取前端的数据,MVC支持自动匹配
		// 2: 调用业务逻辑
		productService.save(product);
		// 3: 跳转到查询页面 (转发与重定向在mvc都不需工程名,项目自动添加)
		return "redirect:/query.jsp";
	}

	@RequestMapping("/update")
	public String update(Product product) {
		System.out.println(product);
		// 2: 调用业务逻辑
		productService.update(product);
		// 3: 跳转到查询页面
		return "redirect:/query.jsp";
	}

	@RequestMapping("/delete")
	public String delete(int id) {
		productService.delete(id);
		// 2: 建议采用原来的关键字进行查询操作(原来关键字存储在session中)
		String keyword = (String)session.getAttribute("keyword");
		List<Product> proList = productService.queryByName(keyword);
		request.setAttribute("proList", proList);
		return "forward:/query.jsp";
	}

	@RequestMapping("/getById")
	public String getById(int id) {
		Product product = productService.getById(id);
		request.setAttribute("product", product);
		return "forward:/update.jsp";
	}

	@RequestMapping("/query")
	public String query(String keyword) {
		session.setAttribute("keyword", keyword);
		// 2: 调用业务逻辑(此处不需要添加%%)
		List<Product> proList = productService.queryByName(keyword);
		request.setAttribute("proList", proList);
		return "forward:/query.jsp";
	}

}
