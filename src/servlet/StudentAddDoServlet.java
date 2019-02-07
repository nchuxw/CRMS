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
 * Servlet implementation class StudentAddDoServlet
 */
@WebServlet("/StudentAddDoServlet")
public class StudentAddDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentAddDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("---add student in Servlet");
		//String id = request.getParameter("id");
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
		
		// 鏋勫缓鏂板鐨凟mployeeBean
		UserBean bean = new UserBean();
		//bean.setUserId(id);
		bean.setName(name);
		bean.setUserNo(userNo);
		bean.setPassword(password);
		bean.setType(type);
		bean.setClassId(classId);
		bean.setDepartment(user.getDepartment());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		out.println(classId);
		UserDao dao = new UserDao();
		try {
			if (dao.addUserInfo(bean)> 0) {			
				out.println("<script type='text/javascript'>");
				out.println("window.alert(\"添加成功!\");");
				out.println("window.location.href='StudentManageServlet?id=" + classId + "';");
				out.println("</script>");
			} else {
				out.println("<script type='text/javascript'>");
				out.println("window.alert(\"添加失败!!\");");
				out.println("window.location.href='student_add.jsp?id=" + classId + "';");
				out.println("</script>");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
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
