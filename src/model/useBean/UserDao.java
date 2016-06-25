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
		Connection conn = DataBaseUtil.getConnection();
		String sql = "select * from tb_user where username = ? and password = ?";
		try{
			PreparedStatement ps = (PreparedStatement)conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				user = new User();
				user.setId(rs.getInt("id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setSex(rs.getString("sex"));
				user.setTel(rs.getString("tel"));
				user.setPassword(rs.getString("photo"));
				user.setEmail(rs.getString("email"));
			}
			rs.close();
			ps.close();
		}catch(Exception e){
			e.printStackTrace();
			
		}finally{
			DataBaseUtil.closeConnection(conn);
		}
		return user;
	}
}
