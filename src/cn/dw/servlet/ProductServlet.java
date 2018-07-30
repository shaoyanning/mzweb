package cn.dw.servlet;

import java.io.IOException;
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

	// 只能用来处理get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	// 处理 post请求
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("----doPost----");
		// 1: 获取前端的数据
		Product product = new Product();
		product.setName(request.getParameter("name"));
		product.setPrice(Double.parseDouble(request.getParameter("money")));
		product.setRemark(request.getParameter("remark"));
		// 2: 调用业务逻辑
		productService.save(product);
		// 3: 跳转到查询页面
	}

}











