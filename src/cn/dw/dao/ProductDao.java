package cn.dw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.StreamingNotifiable;

import cn.dw.model.Product;
import cn.dw.utils.JdbcUtils;

// Java仅仅支持单继承,因此super指代唯一父类
public class ProductDao extends BaseDao {

	// main方法测试的缺点: 1: 不能保留测试痕迹  2：main写测试代码有侵入性
	public static void main(String[] args) {}

	public Product getById(int id) {
		Product product = null;
		String sql = "select * from product where id = ?";
		// 1: 获取connection对象
		JdbcUtils utils = new JdbcUtils();
		Connection conn = null;
		PreparedStatement pre = null;
		// 查询的结果集(存储了的就是返回table结构)
		ResultSet rs = null;
		try {
			conn = utils.getConnection();
			pre = conn.prepareStatement(sql);
			pre.setInt(1, id);
			// rs存储了结果集,即使没有数据,也存储了空结果集
			rs = pre.executeQuery();
			// rs光标(数据库的游标),默认在第一行之前,因为根据id主键查询,最多查询出一条记录(if)
			if (rs.next()) { // next() 把当前光标向下移动一行,如果当前行有记录则返回的true
				// 数据库的每条记录,对应Java的对象
				product = new Product();
				// getInt("id") 获取id列的值并且以int类型
				product.setId(rs.getInt("id"));
				product.setPrice(rs.getDouble("price"));
				product.setDate(rs.getDate("date"));
				product.setRemark(rs.getString("remark"));
				product.setName(rs.getString("name"));
			}
			return product;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				rs.close();
				pre.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

		
	}

	public int update(Product product) {
		// ? 称为占位符
		String sql = "update product set name = ?,price = ?,remark = ? where id = ?";
		return super.executeUpdate(sql,
				new Object[] { product.getName(), product.getPrice(), product.getRemark(), product.getId() });
	}

	public int delete(int id) {
		// ? 称为占位符
		String sql = "delete from product where id = ?";
		return super.executeUpdate(sql, new Object[] { id });
	}

	// 编写一个方法,完成数据的入库操作
	public int save(Product product) {
		// ? 称为占位符
		String sql = "insert into product (name,price,remark) values (?,?,?)";
		int result = super.executeUpdate(sql,
				new Object[] { product.getName(), product.getPrice(), product.getRemark() });
		return result;
	}
}
