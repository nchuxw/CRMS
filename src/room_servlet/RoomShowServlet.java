package room_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.Request;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils.Collections;

import bean.ComputerBean;
import bean.ComputerDao;
import bean.ComputerRoomBean;
import bean.ComputerRoomDao;
import util.StringUtil;

@WebServlet("/RoomShowServlet")
public class RoomShowServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roomId = StringUtil.toCN(req.getParameter("room_id"));
		String type = StringUtil.toCN(req.getParameter("user_type"));
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
		
		java.util.Collections.sort(list);
		
		boolean isUsing = false;
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getSatus().equals(ComputerBean.CUSING) || 
				list.get(i).getSatus().equals(ComputerBean.SUSING)) {
				isUsing = true;
			}
		}
		
		req.setAttribute("room", room);
		req.setAttribute("com_list", list);
		req.setAttribute("is_using", isUsing);
		resp.setContentType("text/html; charset=utf-8");
		if("student".equals(type)) {
			req.getRequestDispatcher("student_room_show.jsp").forward(req, resp);
		}
		else {
			req.getRequestDispatcher("room_show.jsp").forward(req, resp);
		}
			
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
