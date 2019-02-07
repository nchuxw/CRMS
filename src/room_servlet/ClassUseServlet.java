package room_servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassBean;
import bean.ClassDao;
import bean.ClassUseLogBean;
import bean.ClassUseLogDao;
import bean.ComputerBean;
import bean.ComputerDao;
import bean.UserBean;
import util.DateUtil;

@WebServlet("/ClassUseServlet")
public class ClassUseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String class_name = req.getParameter("class_name");
		String roomId = req.getParameter("room_id");
		UserBean user = (UserBean)req.getSession().getAttribute("user");
		if(user==null) {
			System.out.println("用户信息过期，请重新登录");
			resp.sendRedirect("login.html");
			return ;
		}
		ClassBean cla = null;
		try {
			cla = ClassDao.loadClass(class_name, user.getDepartment());
			if(cla == null) {
				System.out.println("该班级不存在");
				resp.sendRedirect("RoomShowServlet?room_id="+roomId);
				return ;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ClassUseLogDao.classUse(roomId);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String time = DateUtil.GetTodayStr();
		ClassUseLogBean claUse = new ClassUseLogBean(cla.getClassId(), time, roomId);
		try {
			ClassUseLogDao.addLog(claUse);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		resp.sendRedirect("RoomShowServlet?room_id="+roomId);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
