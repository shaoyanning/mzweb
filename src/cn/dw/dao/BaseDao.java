package cn.dw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import cn.dw.model.Product;
import cn.dw.utils.JdbcUtils;

// 此类抽取访问数据库的共性代码
public class BaseDao extends Object {
	
	protected ArrayList<Product> queryByName(String sql,Object[] param){
		ArrayList<Product> proList = new ArrayList<Product>();
		JdbcUtils utils = new JdbcUtils();
		Connection conn = null;
		PreparedStatement pre = null;
		// 查询的结果集(存储了的就是返回table结构)
		ResultSet rs = null;
		try {
			// 1: 获取Connection连接对象
			conn = utils.getConnection();
			// 2: PreparedStatement对象
			pre = conn.prepareStatement(sql);
			// 3: 给参数赋值 ? 从1开始
			for (int i = 0; i < param.length; i++) {
				pre.setObject(i+1, param[i]);
			}
			// 4: rs存储了结果集,即使没有数据,也存储了空结果集
			rs = pre.executeQuery();
			// rs光标(数据库的游标),默认在第一行之前,因为根据id主键查询,最多查询出一条记录(if)
			while (rs.next()) { // next() 把当前光标向下移动一行,如果当前行有记录则返回的true
				// 5: 数据库的每条记录,对应Java的对象
				Product product = new Product();
				// getInt("id") 获取id列的值并且以int类型
				product.setId(rs.getInt("id"));
				product.setPrice(rs.getDouble("price"));
				product.setDate(rs.getDate("date"));
				product.setRemark(rs.getString("remark"));
				product.setName(rs.getString("name"));
				proList.add(product);
			}
			return proList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally {
			try {
				// 6: 关闭连接释放资源
				rs.close();
				pre.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	
	}
	

	// new Object[] { product.getName(), product.getPrice(), product.getRemark() }
	// 抽取每个模块 insert update delete
	protected int executeUpdate(String sql, Object[] param) {
		String abc = null;
		JdbcUtils jdbcUtils = new JdbcUtils();
		// 1: 获取数据库连接
		Connection conn = null;
		PreparedStatement pre = null;
		try {
			conn = jdbcUtils.getConnection();
			// 2: 通过connection对象生成预编译sql语句
			pre = conn.prepareStatement(sql);
			// 3: 给?赋值相应的参数(下标从1开始)
			// pre.setString(1, product.getName());
			// pre.setDouble(2, product.getPrice());
			// pre.setString(3, product.getRemark());
			// pre.setInt(4, product.getId());
			for (int i = 0; i < param.length; i++) {
				pre.setObject(i+1, param[i]);
			}
			// 数据库 insert、update、delete都修改数据统称更新,此方法返回的是更新的行数
			return pre.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			System.out.println("无论是否抛出异常,此代码块都会执行,一般用来释放资源");
			// 4: 完成数据库连接关闭操作
			try {
				pre.close();
				conn.close();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}

	}

}
