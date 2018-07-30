package cn.dw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.mysql.jdbc.StreamingNotifiable;

import cn.dw.model.Product;
import cn.dw.utils.JdbcUtils;

// Java仅仅支持单继承,因此super指代唯一父类
public class ProductDao extends BaseDao {

	// main方法测试的缺点: 1: 不能保留测试痕迹 2：main写测试代码有侵入性
	public static void main(String[] args) {
		// 数组限制大小,限制类型
		int[] i = new int[] { 1, 2, 3, 4, 5 };
		// 集合: 不限大小与类型
		ArrayList list = new ArrayList();
		list.add("A");
		list.add(new Date());
		list.add(2);
		// 泛型集合：不限大小,限制类型
		ArrayList<String> sList = new ArrayList<String>();
		sList.add("abc");
		// 三元运算符
		boolean isTrue = 5 > 40 ? true : false;
		System.out.println(isTrue);
	}

	public ArrayList<Product> queryByName(String keyword) {
		String sql = "select * from product where name like ?";
		return super.queryByName(sql, new Object[] { "%" + keyword + "%" });
	}

	public Product getById(int id) {
		String sql = "select * from product where id = ?";
		// 因为是根据id查询,因此集合最多只有一个对象
		ArrayList<Product> proList = super.queryByName(sql,new Object[] {id});
		return proList.size()!=0 ? proList.get(0) : null;
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
