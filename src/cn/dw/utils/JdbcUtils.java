package cn.dw.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// 此类主要用来连接数据库
public class JdbcUtils {
	
	public JdbcUtils() {
		System.out.println("JdbcUtils........");
	}

	// 1: 连接数据库首先需要厂商提供驱动程序(使用说明书)
	// 2: 驱动是已jar包的形式出现的 (lib目录)
	// 3: 在项目运行的时候需要把驱动文件加载到JVM中(仅仅加载一次即可)
	// 4: java中运行一次的代码应该写在静态块中
	static {
		// 当前class文件 JdbcUtils.class在加载到JVM中此块会执行且执行一次
		System.out.println("-----static------");
		// 有些错误在运行时候才能发现：例如连接数据库的账号和密码 , 运行时报错称为异常!
		// 通过类全名,加载当前类信息 
		try {
			System.out.println("1");
			// 此行代码实现了把驱动加载JVM中
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("2");
		} catch (ClassNotFoundException e) {
			// cache用来捕获异常,例如: 发邮箱到管理员，后面跳转到某个页面
			System.out.println("3");
			throw new RuntimeException(e);  // 将异常抛出
		}
	}
	
	// 编写一个方法,此方法将返回一个连接对象(Connection)
	public Connection getConnection() {
		// 编写连接字符串的相关信息
		try {
			return DriverManager.getConnection("jdbc:mysql://localhost:3306/mysql", "root", "root");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}
	}

	
	public static void main(String[] args) {
		JdbcUtils utils = new JdbcUtils();
		Connection conn = null;
		conn = utils.getConnection();
		System.out.println(conn);
	}
	
	
}
