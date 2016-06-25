package cn.control;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.useBean.User;
import model.useBean.UserDao;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				//设置request编码
				request.setCharacterEncoding("UTF-8");
				//设置response 编码
				response.setContentType("text/html");
				response.setCharacterEncoding("UTF-8");
				// 获取用户名
				String username = request.getParameter("username");
				// 获取密码
				String password = request.getParameter("password");
				// 实例化UserDao对象
				UserDao userDao = new UserDao();	
				// 根据用户密码查询用户
				User user = userDao.login(username, password);
				// 判断user是否为空
				if(user != null){
					// 将用户对象放入session中
					request.getSession().setAttribute("user", user);
					// 转发到result.jsp页面
					request.getRequestDispatcher("message.jsp").forward(request, response);
				}else{
					// 登录失败
					request.setAttribute("info", "错误：用户名或密码错误！");
					request.getRequestDispatcher("message.jsp").forward(request, response);
				}
		}
}
