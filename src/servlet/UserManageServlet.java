package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassBean;
import bean.UserBean;
import bean.ClassDao;
import bean.UserDao;
import util.Pager;

/**
 * Servlet implementation class ClassManageServlet
 */
@WebServlet("/UserManageServlet")
public class UserManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		String name = request.getParameter("name");
		String stuNum = request.getParameter("stuNum");
		String minPeriod = request.getParameter("minPeriod");
		String curPage = request.getParameter("cur_page");
		if (curPage == null || "".equals(curPage))
			curPage = "1";
		ClassDao dao = new ClassDao();
		List<ClassBean> list = null;
		Pager pager = new Pager();
		pager.setCurPage(Integer.parseInt(curPage));
		try {
			list = dao.listClass(name,stuNum,minPeriod,pager);
		} catch (Exception e) {
			e.printStackTrace();
		}	
		
		request.setAttribute("name", name);
		request.setAttribute("list", list);
		request.setAttribute("pager", pager);
		
		request.getRequestDispatcher("class_manage.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
