package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;


import bean.UserBean;
import bean.UserDao;
import util.StringUtil;

/**
 * Servlet implementation class EmployeeAddDoServlet
 */
@WebServlet("/RoomadminAddDoServlet")
public class RoomadminAddDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	/**
     * @see HttpServlet#HttpServlet()
     */
    public RoomadminAddDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 处理上传
		System.out.println("---add roomadmin in Servlet");
		//String id = request.getParameter("id");
		String userNo = request.getParameter("userNo");
		String name = StringUtil.toCN(request.getParameter("name")); // 工号
		String password = request.getParameter("password"); // 密码
		//String type = request.getParameter("type"); // 用户类型
		//String classId = request.getParameter("classId"); 
		//String department = StringUtil.toCN(request.getParameter("department"));
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		if(user==null) {
			System.out.println("�û���Ϣ���ڣ������µ�¼");
			response.sendRedirect("login.jsp");
			return ;
		}
		
		// 构建新增的EmployeeBean
		UserBean bean = new UserBean();
		//bean.setUserId(id);
		bean.setName(name);
		bean.setUserNo(userNo);
		bean.setPassword(password);
		bean.setType("comrmer");
		bean.setClassId("2");
		bean.setDepartment(user.getDepartment());
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		UserDao dao = new UserDao();
		try {
			if (dao.addUserInfo(bean)> 0) {
				
				out.println("<script type='text/javascript'>");
				out.println("window.alert(\"��ӳɹ�\");");
				out.println("window.location.href='RoomadminManageServlet';");
				out.println("</script>");
			} else {
				out.println("<script type='text/javascript'>");
				out.println("window.alert(\"���ʧ��!!\");");
				out.println("window.location.href='roomadmin_add.jsp");
				out.println("</script>");
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
