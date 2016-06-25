package model.useBean;

import java.sql.Connection;
import java.sql.ResultSet;
import com.mysql.jdbc.PreparedStatement;

public class UserDao {
	public boolean userIsExist(String username){
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from tb_user where username = ?";
		try{
			//获取PrepareStatement 对象
			PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			if(!rs.next()){
				return true;
			}
			//释放ResultsSet 对象的数据库和JDBC资源
			rs.close();
			//释放PrepareStatement 对象的数据库和JDBC资源
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			DataBaseUtil.closeConnection(conn);
		}
		return false;	
	}
	
	public void saveUser(User user){
		//获取数据库连接对象
		Connection conn = DataBaseUtil.getConnection();
		String sql = "insert into tb_user(username,password,sex,tel,photo,email)"
				+ "values(?,?,?,?,?,?)";
		try{
			PreparedStatement ps = (PreparedStatement)conn.prepareStatement(sql);
			//对sql语句的占位符进行动态的赋值
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getSex());
			ps.setString(4, user.getTel());
			ps.setString(5, user.getPhoto());
			ps.setString(6, user.getEmail());
			ps.executeUpdate();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();	
		}finally {
			DataBaseUtil.closeConnection(conn);
		}
	}
	
	public User login(String username,String password){
		User user = null;
		// 获取数据库连接Connection对象
		Connection conn = DataBaseUtil.getConnection();
		// 根据用户名及密码查询用户信息
		String sql = "select * from tb_user where username = ? and password = ?";
		try {
			// 获取PreparedStatement对象
			PreparedStatement ps = (PreparedStatement)conn.prepareStatement(sql);
			// 对SQL语句的占位符参数进行动态赋值
			ps.setString(1, username);
			ps.setString(2, password);
			// 执行查询获取结果集
			ResultSet rs = ps.executeQuery();
			// 判断结果集是否有效
			if(rs.next()){
				// 实例化一个用户对象
				user = new User();
				// 对用户对象属性赋值
				//user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setTel(rs.getString("tel"));
				user.setPhoto(rs.getString("photo"));
				user.setEmail(rs.getString("email"));
			}
			// 释放此 ResultSet 对象的数据库和 JDBC 资源
			rs.close();
			// 释放此 PreparedStatement 对象的数据库和 JDBC 资源
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			// 关闭数据库连接
			DataBaseUtil.closeConnection(conn);
		}
		return user;
	}
}
