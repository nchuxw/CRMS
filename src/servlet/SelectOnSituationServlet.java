package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import bean.UserDao;
import bean.UserUseLog;
import util.Pager;

/**
 * Servlet implementation class SelectOnSituationServlet
 */
@WebServlet("/SelectOnSituationServlet")
public class SelectOnSituationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectOnSituationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String room = request.getParameter("room");
		String userId = request.getParameter("user_id");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		String curPage = request.getParameter("cur_page");
		if (curPage == null || "".equals(curPage))
			curPage = "1";
		UserDao dao = new UserDao();
		List<UserUseLog> list = null;
		Pager pager = new Pager();
		pager.setCurPage(Integer.parseInt(curPage));
		try {
			list = dao.listUseLog(room, userId,start,end, pager);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		request.setAttribute("room", room);
		request.setAttribute("userId", userId);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		
		request.getRequestDispatcher("select_on_situation.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
