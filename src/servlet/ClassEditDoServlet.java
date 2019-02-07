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
 * Servlet implementation class ClassEditDoServlet
 */
@WebServlet("/ClassEditDoServlet")
public class ClassEditDoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassEditDoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("---update tclass in Servlet");
		String name = request.getParameter("name");
		//String stuNum = request.getParameter("stuNum");
		String minPeriod = StringUtil.toCN(request.getParameter("minPeriod")); // 宸ュ彿
		String id = request.getParameter("id"); 
		
		ClassDao dao = new ClassDao();
		ClassBean bean = new ClassBean();
		bean.setClassId(id);
		bean.setName(name);
		bean.setMinPeriod(Integer.parseInt(minPeriod));
		//bean.setStuNum(Integer.parseInt(stuNum));
		
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
			out.println("window.location.href='UserManageServlet';");
			out.println("</script>");
		} else if (result == -2){
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"改班级名已存在!!\");");
			out.println("window.location.href='UserManageServlet?id=" + id + "';");
			out.println("</script>");
		}else {
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"修改失败!!\");");
			out.println("window.location.href='UserManageServlet?id=" + id + "';");
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
