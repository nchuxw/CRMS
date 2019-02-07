package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassBean;
import bean.UserBean;
import bean.ClassDao;
import bean.UserDao;
import util.*;
/**
 * Servlet implementation class ClassEditServlet
 */
@WebServlet("/ClassEditServlet")
public class ClassEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		if (id == null || "".equals(id))
		{
			response.sendRedirect("UserManageServlet");
			return;
		}
		
		// 根据id获取要修改的员工信息bean
		ClassDao dao = new ClassDao();
		ClassBean bean = null;
		try {
			bean = dao.loadClass(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 将bean作为参数传到employee_edit.jsp页面
		request.setAttribute("bean", bean);
		
		// 带参数跳转到employee_edit.jsp
		request.getRequestDispatcher("class_edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
