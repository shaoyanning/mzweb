package cn.dw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

import cn.dw.model.Product;
import cn.dw.utils.JdbcUtils;

// 此类抽取访问数据库的共性代码
public class BaseDao extends Object {

//	public static void main(String[] args) {
//		// 父类的引用可以指向子类的对象
//		int i = 0;
//		System.out.println(i+1);   // 1  
//		System.out.println(i);     // 0
//		System.out.println(i++);   // 0
//		System.out.println(++i);   // 2
//	}

	// new Object[] { product.getName(), product.getPrice(), product.getRemark() }
	// 抽取每个模块 insert update delete
	protected int executeUpdate(String sql, Object[] param) {
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
