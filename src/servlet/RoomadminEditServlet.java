package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import bean.UserDao;

/**
 * Servlet implementation class EmployeeEdit
 */
// 这里取消了用注解的方式，配置信息在web.xml文件�?
@WebServlet("/RoomadminEditServlet")
public class RoomadminEditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomadminEditServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// �?1步，获取要修改的id
		String id = request.getParameter("id");
		if (id == null || "".equals(id))
		{
			response.sendRedirect("RoomadminManageServlet");
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
		request.getRequestDispatcher("roomadmin_edit.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
