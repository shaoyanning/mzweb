package cn.dw.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

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
		 String sql = "select * from product where name like ?";
		 // 如果数据的字段、与当前类的属性名称一致则可以采用 BeanPropertyRowMapper 自动匹配
		return jdbcTemplate.query(sql, new Object[] {"%" + keyword + "%"}, new BeanPropertyRowMapper<Product>(Product.class));
	}

	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		// 直接new接口(必须要实现接口定义的方法), forOject如果没有查询出来会抛出异常
		return jdbcTemplate.queryForObject(sql, new Object[]{id},new RowMapper<Product>() {
			@Override
			public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
				Product product = new Product();
				product.setId(rs.getInt("id"));
				product.setName(rs.getString("name"));
				product.setRemark(rs.getString("remark"));
				product.setPrice(rs.getDouble("price"));
				return product;
			}
		});
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
