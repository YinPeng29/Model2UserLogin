package cn.control;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.useBean.User;
import model.useBean.UserDao;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegServlet() {
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
		// 设置response 编码
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String sex = request.getParameter("sex");
		String tel = request.getParameter("tel");
		String photo = request.getParameter("photo");
		String email = request.getParameter("email");
		///实例化UserDao对象
		UserDao userDao = new UserDao();
		if(username!=null && !username.isEmpty()){
			if(userDao.userIsExist(username)){
				//实例化一个User对象
				User user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setSex(sex);
				user.setTel(tel);
				user.setPhoto(photo);
				user.setEmail(email);
				userDao.saveUser(user);
				request.setAttribute("info","恭喜，注册成功！<br>");
			}else{
				request.setAttribute("info","抱歉，此用户已经存在");
			}
		}
		//转发到 message.jsp 页面
		request.getRequestDispatcher("message.jsp").forward(request,response);
		doGet(request, response);
	}

}
