package servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassDao;
import bean.UserDao;
import util.*;

/**
 * Servlet implementation class ClassesDeleteServlet
 */
@WebServlet("/ClassesDeleteServlet")
public class ClassesDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClassesDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] ids = request.getParameterValues("ck");
		 ClassDao dao = new ClassDao(); 
		 int result = 0;
			try {
				result = dao.deletes(ids);
			}catch (Exception ex) {
				ex.printStackTrace();
				result = -1;
			}
			
			response.setContentType("text/html;charset=utf-8");
			PrintWriter out = response.getWriter();
			
			if (result > 0) {			
				out.println("<script type='text/javascript'>");
				out.println("window.alert(\"ɾ���ɹ�!\");");
				out.println("window.location.href='UserManageServlet';");
				out.println("</script>");
			} else {
				out.println("<script type='text/javascript'>");
				out.println("window.alert(\"ɾ��ʧ��!!\");");
				out.println("window.location.href='UserManageServlet';");
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
