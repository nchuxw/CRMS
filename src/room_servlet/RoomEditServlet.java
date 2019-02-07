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
import util.StringUtil;

@WebServlet("/RoomEditServlet")
public class RoomEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String roomId = StringUtil.toCN(req.getParameter("room_id"));
		
		ComputerRoomBean room = null;
		try {
			room = ComputerRoomDao.loadRoom(roomId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		List<ComputerBean> list = null;
		try {
			list = ComputerDao.loadList(roomId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		int comNum = list.size();
		String []layoutX = new String[comNum];
		String []layoutY = new String[comNum];
		for(int i = 0; i < comNum; i++) {
			layoutX[i] = String.valueOf(list.get(i).getLayoutX());
			layoutY[i] = String.valueOf(list.get(i).getLayoutY());
		}
		
		req.setAttribute("room", room);
		req.setAttribute("com_num", comNum);
		req.setAttribute("layoutX", layoutX);
		req.setAttribute("layoutY", layoutY);
		resp.setContentType("text/html; charset=utf-8");
		req.getRequestDispatcher("room_edit.jsp").forward(req, resp);
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
