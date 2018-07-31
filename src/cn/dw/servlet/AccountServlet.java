package cn.dw.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
       
    public AccountServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 完成登录功能的验证(假设账号的密码相同则代表登录成功)
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		if(username.equals(password)) {
			// 此处是模拟,应该AccountService --> AccountDao --> db 来完成登录的验证操作
			// 登录成功,把用户信息存储到session中
			HttpSession session = request.getSession();
			session.setAttribute("admin", username);
			// 登录成功,则跳转到后台首页
			response.sendRedirect(request.getContextPath() + "/admin/index.jsp");
		}else {
			// 登录失败,应该给客户提示
			request.setAttribute("error", "账号密码不正确");
			// 当前accountServlet与login.jsp共享错误消息,因此用转发操作
			RequestDispatcher dispatcher = request.getRequestDispatcher("/login.jsp");
			dispatcher.forward(request, response);
		}
	}

}







