package servlet;

import java.io.IOException;
import java.net.InetAddress;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.LoginLogDao;
import bean.UserBean;
import bean.UserDao;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String user_id = req.getParameter("username");
		String password = req.getParameter("password");
		System.out.println(user_id + ", " + password);
		UserBean user = null;
		InetAddress ia = null;
		try {
			ia = InetAddress.getLocalHost();
			String localip = ia.getHostAddress();
			LoginLogDao.add(user_id, localip);
			user = UserDao.loadUser(user_id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		if (user == null) {
			System.out.println("用户不存在");
		} else if (password.equals(user.getPassword())) {
			req.getSession().setAttribute("user", user);
			req.getRequestDispatcher("index.jsp").forward(req, resp);
		}

		else {
			System.out.println("密码错误");
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
