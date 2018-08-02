package cn.dw.dao;

import java.util.List;

import javax.naming.Context;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.dw.model.Product;
import cn.dw.service.ProductService;

public class ProductDaoTest {

	private static ProductService service = null;
	private static ApplicationContext context = null; 

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		System.out.println("在所有测试方法之前有运行,通常用来初始化测试对象.......");
		context = new ClassPathXmlApplicationContext("spring-bean.xml");
//		dao = (ProductDao)context.getBean("productDao");
		service = context.getBean("ps", ProductService.class);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		System.out.println("在所有测试方法之后有运行,通常用来销毁.......");
		service = null;
	}
	
	@Test
	public void testQueryName() {
		List<Product> proList = service.queryByName("");
		for(Product temp:proList) {
			System.out.println(temp);
		}
	}

	@Test
	public void testGetById() {
		Product product = service.getById(1);
		System.out.println(product);
	}

	@Test
	public void testUpdate() {
		Product product = new Product();
		product.setId(1);
		product.setName("锤子手机2");
		product.setPrice(2400.00);
		product.setRemark("商品备注！");
		service.update(product);
	}

	@Test
	public void testDelete() {
		int result = service.delete(4);
		System.out.println(result);
	}

	@Test
	public void testSave() {
		Product product = new Product();
		product.setName("小米手机4");
		product.setPrice(2400.00);
		product.setRemark("商品备注4！");
		System.out.println(service.save(product));
	}

}
