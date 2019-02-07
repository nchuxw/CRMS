package room_servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ComputerRoomDao;

@WebServlet("/RoomDeleteServlet")
public class RoomDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String []list = req.getParameterValues("choose");
		
		for(int i = 0; i < list.length; i++) {
			try {
				ComputerRoomDao.deleteRoom(list[i]);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		System.out.println("É¾³ý³É¹¦£¡");
		resp.sendRedirect("RoomListServlet");
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
