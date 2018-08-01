package cn.dw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.dw.model.Product;
import cn.dw.service.ProductService;

/**
 * Servlet、Filter、Listener 都是web组件，默认是单例模式，单例模式都要解决线程安全问题
 * 
 * 后面有了spring则 service dao都应该是单例模式
 */
@WebServlet("/ProductServlet") // 可以接受前端请求的地址
public class ProductServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	// 由于Servlet是单例,因此普通属性也只有一份,但是前台的请求是并发的。如果用单例的属性存储前端的数据可能会出现线程安全
//	private String keyword = null;
	// jsp ---> servlet --> service --> dao --> db
	private ProductService productService = new ProductService();

	public ProductServlet() {
		super();
		System.out.println("ProductServlet()......");
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		super.destroy();
		System.out.println("ProductServlet destroy()......");
	}

	// 只能用来处理get请求 <a>也是get请求
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	// 处理 post请求
	// request: 封装了从客户端到服务端的请求数据
	// response: 封装从服务器端返回给客户端的数据
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
//		request.setCharacterEncoding("UTF-8");
		// 通过前端type的值,确定提交的请求类型
		String type = request.getParameter("type");
		if (type.equals("query")) {
			// 1: 获取查询的关键字 keyword，把关键存储在每个用户自己session中
			String keyword = request.getParameter("keyword");
			session.setAttribute("keyword", keyword);
			// 2: 调用业务逻辑(此处不需要添加%%)
			List<Product> proList = productService.queryByName(keyword);
			request.setAttribute("proList", proList);
			// 3: 重定向,让用户再去访问query.jsp (会生成一个新request对象)
			// response.sendRedirect(request.getContextPath() + "/query.jsp");
			// 转发请求,会把之前的request请求转发第二个页面,只能转发到系统内部资源(默认添加工程名)
			RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
			// 共享第一次请求的request对象
			dispatcher.forward(request, response);
		} else if (type.equals("save")) {
			System.out.println("----doPost----");
			// 1: 获取前端的数据
			Product product = new Product();
			product.setName(request.getParameter("name"));
			product.setPrice(Double.parseDouble(request.getParameter("money")));
			product.setRemark(request.getParameter("remark"));
			// 2: 调用业务逻辑
			productService.save(product);
			// 3: 跳转到查询页面
//			response.sendRedirect("/mzweb/query.jsp");
			response.sendRedirect(request.getContextPath() + "/query.jsp");
		} else if (type.equals("delete")) {
			// 1: 获取删除id
			int id = Integer.parseInt(request.getParameter("id"));
			productService.delete(id);
			// 2: 建议采用原来的关键字进行查询操作(原来关键字存储在session中)
			String keyword = (String)session.getAttribute("keyword");
			List<Product> proList = productService.queryByName(keyword);
			request.setAttribute("proList", proList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/query.jsp");
			dispatcher.forward(request, response);
		}else if(type.equals("getById")) {
			int id = Integer.parseInt(request.getParameter("id"));
			Product product = productService.getById(id);
			request.setAttribute("product", product);
			// 由于当前servlet与update.jsp共享数据,因此只能用转发请求
			RequestDispatcher dispatcher = request.getRequestDispatcher("/update.jsp");
			dispatcher.forward(request, response);
		}else if(type.equals("update")) {
			Product product = new Product();
			product.setId(Integer.parseInt(request.getParameter("id")));
			product.setName(request.getParameter("name"));
			product.setPrice(Double.parseDouble(request.getParameter("money")));
			product.setRemark(request.getParameter("remark"));
			// 2: 调用业务逻辑
			productService.update(product);
			// 3: 跳转到查询页面
			response.sendRedirect(request.getContextPath() + "/query.jsp");
		}

	}

}
