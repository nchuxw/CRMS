package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.CountByRoomInfo;
import bean.UserDao;
import bean.UserUseLog;
import util.Pager;

/**
 * Servlet implementation class CountOnSituationByRoomServlet
 */
@WebServlet("/CountOnSituationByRoomServlet")
public class CountOnSituationByRoomServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CountOnSituationByRoomServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String room = request.getParameter("room");
		
		UserDao dao = new UserDao();
		List<CountByRoomInfo> list = null;
		//Pager pager = new Pager();
		//pager.setCurPage(Integer.parseInt(curPage));
		try {
			list = dao.listByRoomInfo(room,start,end);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		request.setAttribute("room", room);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("list", list);
		
		request.getRequestDispatcher("count_on_situation_byroom.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
