package cn.dw.dao;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;

import cn.dw.model.Product;

// Java仅仅支持单继承,因此super指代唯一父类
public class ProductDao {
	// 通过spring把jdbctTemplate 注入进来
	private JdbcTemplate jdbcTemplate = null;

	// 必须要有set方法
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	public List<Product> queryByName(String keyword) {
		// String sql = "select * from product where name like ?";
		// return super.queryByName(sql, new Object[] { "%" + keyword + "%" });
		return null;
	}

	public Product getById(int id) {
		// String sql = "select * from product where id = ?";
		// // 因为是根据id查询,因此集合最多只有一个对象
		// List<Product> proList = super.queryByName(sql,new Object[] {id});
		// return proList.size()!=0 ? proList.get(0) : null;
		return null;
	}

	public int update(Product product) {
		String sql = "update product set name = ?,price = ?,remark = ? where id = ?";
		return jdbcTemplate.update(sql, new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId()});
	}

	public int delete(int id) {
		// ? 称为占位符
		String sql = "delete from product where id = ?";
		return jdbcTemplate.update(sql, new Object[] { id });
	}

	// 编写一个方法,完成数据的入库操作
	public int save(Product product) {
		// ? 称为占位符
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		return jdbcTemplate.update(sql,new Object[] { product.getName(), product.getPrice(), product.getRemark() });
	}
}
