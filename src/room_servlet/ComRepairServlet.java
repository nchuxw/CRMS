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

@WebServlet("/ComRepairServlet")
public class ComRepairServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String comId = req.getParameter("com_id");
		ComputerBean com = null;
		try {
			com = ComputerDao.loadComputer(comId);
			if(com.getSatus().equals(ComputerBean.REPAIR)) {
				ComputerDao.updataSatus(comId, ComputerBean.USABLE);
			}
			else {
				ComputerDao.updataSatus(comId, ComputerBean.REPAIR);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		resp.sendRedirect("RoomShowServlet?room_id="+com.getRoomId());
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
	
}
