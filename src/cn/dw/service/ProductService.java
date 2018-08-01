package cn.dw.service;

import java.util.ArrayList;
import java.util.List;

import cn.dw.dao.ProductDao;
import cn.dw.model.Product;

// 业务逻辑 --> 数据访问层
public class ProductService {

	ProductDao productDao = new ProductDao();

	// 正常此方法应该有项目的业务
	public int save(Product product) {
		// 目前没有业务逻辑因此直接调用dao即可
		return productDao.save(product);
	}

	public List<Product> queryByName(String keyword) {
		return productDao.queryByName(keyword);
	}

	public int delete(int id) {
		return productDao.delete(id);
	}
	
	public Product getById(int id) {
		return productDao.getById(id);
	}
	
	public int update(Product product) {
		return productDao.update(product);
	}
}
