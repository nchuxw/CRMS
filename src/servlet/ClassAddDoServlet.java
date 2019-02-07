package servlet;

import java.io.IOException;
import java.io.PrintWriter;

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
 * Servlet implementation class ClassAddDoServlet
 */
@WebServlet("/ClassAddDoServlet")
public class ClassAddDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassAddDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("---add tclass in Servlet");
		//String id = request.getParameter("id");
		//String stuNum = request.getParameter("stuNum");
		String name = StringUtil.toCN(request.getParameter("name")); // 宸ュ彿
		String minPeriod = request.getParameter("minPeriod"); // 瀵嗙爜
		UserBean user = (UserBean)request.getSession().getAttribute("user");
		if(user==null) {
			System.out.println("用户信息过期，请重新登录");
			response.sendRedirect("login.jsp");
			return ;
		}

		
		
		// 鏋勫缓鏂板鐨凟mployeeBean
		ClassBean bean = new ClassBean();
		//bean.setUserId(id);
		bean.setName(name);
		//bean.setStuNum(Integer.parseInt(stuNum));
		bean.setDepartment(user.getDepartment());
		bean.setMinPeriod(Integer.parseInt(minPeriod));
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();

		ClassDao dao = new ClassDao();
		try {
			if (dao.addClassInfo(bean)> 0) {
				
				out.println("<script type='text/javascript'>");
				out.println("window.alert(\"添加成功！\");");
				out.println("window.location.href='UserManageServlet';");
				out.println("</script>");
			}else {
				out.println("<script type='text/javascript'>");
				out.println("window.alert(\"添加失败!!\");");
				out.println("window.location.href='class_add.jsp");
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
