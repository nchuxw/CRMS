package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.UserBean;
import bean.UserDao;
import util.StringUtil;

/**
 * Servlet implementation class StudentEditDoServlet
 */
@WebServlet("/StudentEditDoServlet")
public class StudentEditDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentEditDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("---update student in Servlet");
		String id = request.getParameter("id");
		String userNo = request.getParameter("userNo");
		String name = StringUtil.toCN(request.getParameter("name")); // 宸ュ彿
		String password = request.getParameter("password"); // 瀵嗙爜
		String type = request.getParameter("type"); // 鐢ㄦ埛绫诲瀷
		String classId = request.getParameter("classId"); 
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
		bean.setType(type);
		bean.setClassId(classId);
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
			out.println("window.location.href='StudentManageServlet?id=" + classId + "';");
			out.println("</script>");
		} else {
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"修改失败!!\");");
			out.println("window.location.href='StudentEditServlet?id=" + id + "';");
			out.println("</script>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
