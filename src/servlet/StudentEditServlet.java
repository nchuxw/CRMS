package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import bean.UserDao;

/**
 * Servlet implementation class StudentEditServlet
 */
@WebServlet("/StudentEditServlet")
public class StudentEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentEditServlet() {
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
			response.sendRedirect("StudentManageServlet");
			return;
		}
		
		// 根据id获取要修改的员工信息bean
		UserDao dao = new UserDao();
		UserBean bean = null;
		try {
			bean = dao.loadUser(id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 将bean作为参数传到employee_edit.jsp页面
		request.setAttribute("bean", bean);
		
		// 带参数跳转到employee_edit.jsp
		request.getRequestDispatcher("student_edit.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
