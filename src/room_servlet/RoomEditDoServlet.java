package room_servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ComputerRoomDao;
import util.StringUtil;

@WebServlet("/RoomEditDoServlet")
public class RoomEditDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String roomId = StringUtil.toCN(req.getParameter("room_id"));
		try {
			ComputerRoomDao.deleteRoom(roomId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		req.getRequestDispatcher("RoomAddServlet").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}

}
