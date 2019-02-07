package room_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ComputerBean;
import bean.ComputerDao;
import bean.ComputerRoomBean;
import bean.ComputerRoomDao;
import bean.DepartmentDao;
import bean.UserBean;
import util.StringUtil;

@WebServlet("/RoomListServlet")
public class RoomListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String type = StringUtil.toCN(req.getParameter("user_type"));
		UserBean user = (UserBean)req.getSession().getAttribute("user");
		if(user==null) {
			System.out.println("用户信息过期，请重新登录");
			resp.sendRedirect("login.jsp");
			return ;
		}
		List<ComputerRoomBean> list = null;
		try {
			list = ComputerRoomDao.loadList(user.getDepartment());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < list.size(); i++) {
			try {
				if(!DepartmentDao.isUsable(list.get(i).getUnuseTime())) {
					ComputerDao.updataRoomSatus(list.get(i).getRoomId(), ComputerBean.UNUSE);
				}
				else {
					ComputerDao.roomUsable(list.get(i).getRoomId());
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		req.setAttribute("room_list", list);
		resp.setContentType("text/html; charset=utf-8");
		if("student".equals(type)) {
			req.getRequestDispatcher("student_room_list.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("room_list.jsp").forward(req, resp);
		}
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(req, resp);
	}
}
