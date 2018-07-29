package cn.dw.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.mysql.jdbc.StreamingNotifiable;

import cn.dw.model.Product;
import cn.dw.utils.JdbcUtils;

// Java仅仅支持单继承,因此super指代唯一父类
public class ProductDao extends BaseDao {

	public static void main(String[] args) {
		ProductDao dao = new ProductDao();
//		Product product = new Product();
//		product.setId(4);
//		product.setName("小米手机4");
//		product.setPrice(2400.00);
//		product.setRemark("商品备注4！");
//		 System.out.println(dao.update(product));
//		 int result = dao.save(product);
		// System.out.println("result:" + result);
		int result = dao.delete(4);
		System.out.println(result);
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
