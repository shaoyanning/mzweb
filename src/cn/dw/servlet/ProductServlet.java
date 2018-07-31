package cn.dw.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.dw.model.Product;
import cn.dw.service.ProductService;

/**
 * Servlet implementation class ProductServlet
 */
@WebServlet("/ProductServlet") // 可以接受前端请求的地址
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// jsp ---> servlet --> service --> dao --> db
	private ProductService productService = new ProductService();

	public ProductServlet() {
		super();
	}

	// 只能用来处理get请求   <a>也是get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 1: 获取查询的关键字 keyword
		String keyword = request.getParameter("keyword");
		// 2: 调用业务逻辑(此处不需要添加%%)
		ArrayList<Product> proList = productService.queryByName(keyword);
		request.setAttribute("proList", proList);
		// 3: 重定向,让用户再去访问query.jsp (会生成一个新request对象)
//		response.sendRedirect(request.getContextPath() + "/query.jsp");
		// 转发请求,会把之前的request请求转发第二个页面,只能转发到系统内部资源(默认添加工程名)
		RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
		// 共享第一次请求的request对象
		dispatcher.forward(request, response);
		
	}

	// 处理 post请求
	// request: 封装了从客户端到服务端的请求数据
	// response: 封装从服务器端返回给客户端的数据
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		System.out.println("----doPost----");
		// 1: 获取前端的数据
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setPrice(Double.parseDouble(request.getParameter("money")));
		product.setRemark(request.getParameter("remark"));
		// 2: 调用业务逻辑
		productService.save(product);
		// 3: 跳转到查询页面
//		response.sendRedirect("/mzweb/query.jsp");
		response.sendRedirect(request.getContextPath() + "/query.jsp");
	}

}











