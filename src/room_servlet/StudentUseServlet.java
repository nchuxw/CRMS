package room_servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ComputerBean;
import bean.ComputerDao;
import bean.UserBean;
import bean.UserDao;
import bean.UserLogDao;
import bean.UserUseLog;
import util.DateUtil;

@WebServlet("/StudentUseServlet")
public class StudentUseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String comId = req.getParameter("com_id");
		String userNo = req.getParameter("user_no");
		UserBean user = (UserBean)req.getSession().getAttribute("user");
		if(user==null) {
			System.out.println("用户信息过期，请重新登录");
			resp.sendRedirect("login.jsp");
			return ;
		}
		UserBean stu = null;
		ComputerBean com = null;
		try {
			stu = UserDao.loadUser(userNo, user.getDepartment());
			com = ComputerDao.loadComputer(comId);
			if(stu == null) {
				System.out.println("学号' " + userNo +  " '不存在");
				resp.sendRedirect("RoomShowServlet?room_id=" + com.getRoomId());
				return ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		if(com != null && com.getSatus().equals(ComputerBean.USABLE)) {
			UserUseLog log = new UserUseLog(stu.getUserId(), DateUtil.GetTodayStr(), comId, com.getRoomId());
			try {
				ComputerDao.updataSatus(comId, ComputerBean.SUSING);
				UserLogDao.addLog(log);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			resp.sendRedirect("RoomShowServlet?room_id=" + com.getRoomId());
		}
		else {
			System.out.println("上机失败");
		}
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
