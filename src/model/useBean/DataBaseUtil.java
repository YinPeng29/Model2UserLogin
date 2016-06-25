package model.useBean;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.jasper.tagplugins.jstl.core.Out;

import com.mysql.fabric.Response;

public class DataBaseUtil {
	/**
	 * 获取数据连接
	 * @return Connectioin对象 
	 * */
	public static Connection getConnection(){
		
		Connection conn = null;
		String driverName = "com.mysql.jdbc.Driver";
		String url="jdbc:mysql://localhost:3306/db_userinfo";
		String username="root";
		String password = "root";
		try{
			Class.forName(driverName);
			//conn 已经声明，不用 再次声明，否则会出现错误
			conn = DriverManager.getConnection(url,username,password);
		}catch(Exception e){
			e.printStackTrace();
			}
		return conn;
	}
	
	/*
	 * 关闭数据库连接
	 * @param conn Connection对象
	 * getConnection 和 closeConnection 方法均为静态方法都可以直接调用
	 * */
	public static void closeConnection(Connection conn){
		if(conn!=null){
			try{
				conn.close();
			}catch(SQLException e){
				e.printStackTrace();
			}
		}
	}
}