package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import bean.UserBean;
import bean.UserDao;
import util.*;

/**
 * Servlet implementation class EmployeeEditDoServlet
 */
@WebServlet("/RoomadminEditDoServlet")
public class RoomadminEditDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomadminEditDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("---update roomadmin in Servlet");
		String id = request.getParameter("id");
		String userNo = request.getParameter("userNo");
		String name = StringUtil.toCN(request.getParameter("name")); // 宸ュ彿
		String password = request.getParameter("password"); // 瀵嗙爜
		//String type = request.getParameter("type"); // 鐢ㄦ埛绫诲瀷
		//String classId = request.getParameter("classId"); 
		//String department = StringUtil.toCN(request.getParameter("department"));
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		if(user==null) {
			System.out.println("用户信息过期，请重新登录");
			response.sendRedirect("login.jsp");
			return ;
		}
				
		UserDao dao = new UserDao();
		UserBean bean = new UserBean();
		bean.setUserId(id);
		bean.setName(name);
		bean.setUserNo(userNo);
		bean.setPassword(password);
		bean.setType("comrmer");
		bean.setClassId("2");
		bean.setDepartment(user.getDepartment());
		
		int result = 0;
		try {
			result = dao.update(bean);
		}catch (Exception ex) {
			ex.printStackTrace();
			result = -1;
		}

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		if (result > 0) {
			
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"修改成功\");");
			out.println("window.location.href='RoomadminManageServlet';");
			out.println("</script>");
		} else {
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"修改失败!!\");");
			out.println("window.location.href='RoomadminEditServlet?id=" + id + "';");
			out.println("</script>");
		}
	}

}
