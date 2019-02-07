package room_servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassUseLogDao;
import bean.ComputerBean;
import bean.ComputerDao;
import bean.UserDao;
import bean.UserLogDao;
import util.DateUtil;

@WebServlet("/AllUnuseServlet")
public class AllUnuseServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String roomId = req.getParameter("room_id");
		List<ComputerBean> list = new ArrayList<ComputerBean>();
		try {
			list = ComputerDao.loadList(roomId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			ClassUseLogDao.classDeplane(roomId);
			ClassUseLogDao.updateEndTime(roomId, DateUtil.GetTodayStr());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(int i = 0; i < list.size(); i++) {
			if(list.get(i).getSatus().equals(ComputerBean.SUSING)) {
				try {
					ComputerDao.updataSatus(list.get(i).getComId(), ComputerBean.USABLE);
					UserLogDao.updateEndTime(list.get(i).getComId(), DateUtil.GetTodayStr());
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		resp.sendRedirect("RoomShowServlet?room_id=" + roomId);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
