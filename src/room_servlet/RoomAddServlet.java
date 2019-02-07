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
import bean.ComputerRoomBean;
import bean.ComputerRoomDao;
import bean.UserBean;
import util.StringUtil;

@WebServlet("/RoomAddServlet")
public class RoomAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String name = StringUtil.toCN(req.getParameter("name"));
		int width = Integer.parseInt("0" + StringUtil.toCN(req.getParameter("width")));
		int height = Integer.parseInt("0" + StringUtil.toCN(req.getParameter("height")));
		String config = StringUtil.toCN(req.getParameter("config"));
		String software = StringUtil.toCN(req.getParameter("software"));
		String liabler = StringUtil.toCN(req.getParameter("liabler"));
		int comNum = Integer.parseInt(req.getParameter("com_num"));
		String []comNo = req.getParameterValues("com_no");
		String []layoutX = req.getParameterValues("layoutX");
		String []layoutY = req.getParameterValues("layoutY");
		UserBean user = (UserBean)req.getSession().getAttribute("user");
		if(user==null) {
			System.out.println("用户信息过期，请重新登录");
			resp.sendRedirect("login.html");
			return ;
		}
		String department = user.getDepartment();
		ComputerRoomBean room = new ComputerRoomBean(name, width, height, department, config, software, liabler);
		try {
			if(ComputerRoomDao.loadRoom(room.getName(), room.getDepartment()) != null) {
				System.out.println("存在名字同的机房");
				req.setAttribute("room", room);
				req.setAttribute("com_num", comNum);
				req.setAttribute("layoutX", layoutX);
				req.setAttribute("layoutY", layoutY);
				req.getRequestDispatcher("room_add.jsp").forward(req, resp);
				return ;
			}
			ComputerRoomDao.addRoom(room);
			room = ComputerRoomDao.loadRoom(room.getName(), room.getDepartment());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		String roomId = room.getRoomId();
		System.out.println("电脑数：" + comNum);
		for(int i = 0; i < comNum; i++) {
			System.out.println(""+comNo[i]+":("+layoutX[i]+","+layoutY[i]+")");
			int x = Integer.parseInt(layoutX[i]);
			int y = Integer.parseInt(layoutY[i]);
			ComputerBean com = new ComputerBean(roomId, comNo[i], x, y);
			try {
				ComputerDao.addComputer(com);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		System.out.println("添加成功！");
		resp.sendRedirect("RoomListServlet");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
