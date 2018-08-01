package cn.dw.listener;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import cn.dw.model.Product;
import cn.dw.service.ProductService;
// ServletContext: 就是当前项目上下文环境(mzweb+tomcat)
@WebListener  // 配置当前类为监听器
public class InitDataListener implements ServletContextListener {
	
	private ProductService productService = new ProductService();
	
	@Override
	public void contextInitialized(ServletContextEvent event) {
		System.out.println("当前tomcat启动此项目时此方法会执行");
		// 此处实现项目初始化配置文件加载
		ServletContext application = event.getServletContext();
		System.out.println("app:" + application);
		List<Product> proList = productService.queryByName("");
		application.setAttribute("proList", proList);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		System.out.println("当前tomcat关闭此项目时此方法会执行");
	}

	

}
