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
import util.Pager;

/**
 * Servlet implementation class StudentManageServlet
 */
@WebServlet("/StudentManageServlet")
public class StudentManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String id = request.getParameter("id");
		String userNo = request.getParameter("userNo");
		String name = request.getParameter("name");
		String curPage = request.getParameter("cur_page");
		if (curPage == null || "".equals(curPage))
			curPage = "1";
		UserDao dao = new UserDao();
		List<UserBean> list = null;
		Pager pager = new Pager();
		pager.setCurPage(Integer.parseInt(curPage));
		try {
			list = dao.listStudent(userNo, name,id, pager);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		request.setAttribute("classid", id);
		request.setAttribute("userNo", userNo);
		request.setAttribute("name", name);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		
		request.getRequestDispatcher("student_manage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
