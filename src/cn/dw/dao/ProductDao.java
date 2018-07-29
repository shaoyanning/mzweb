package cn.dw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.StreamingNotifiable;

import cn.dw.model.Product;
import cn.dw.utils.JdbcUtils;

public class ProductDao {
	
	public static void main(String[] args) {
		ProductDao dao = new ProductDao();
		Product product = new Product();
		product.setName("笔记本电脑");
		product.setPrice(4500.00);
		product.setRemark("商品备注！");
		int result = dao.save(product);
		System.out.println("result:" + result);
	}

	// 编写一个方法,完成数据的入库操作
	public int save(Product product) {
		// ? 称为占位符
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		JdbcUtils jdbcUtils = new JdbcUtils();
		// 1: 获取数据库连接
		Connection conn = jdbcUtils.getConnection();
		try {
			// 2: 通过connection对象生成预编译sql语句
			PreparedStatement pre = conn.prepareStatement(sql);
			// 3: 给?赋值相应的参数(下标从1开始)
			pre.setString(1, product.getName());
			pre.setDouble(2, product.getPrice());
			pre.setString(3, product.getRemark());
			// 数据库 insert、update、delete都修改数据统称更新,此方法返回的是更新的行数
			return pre.executeUpdate(); 
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		// 4: 完成数据库连接关闭操作
	}
}
