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

/**
 * Servlet implementation class StudentsDeleteServlet
 */
@WebServlet("/StudentsDeleteServlet")
public class StudentsDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentsDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String[] ids = request.getParameterValues("ck");
		String id = request.getParameter("id");
		//System.out.print("deletestu:"+classId);
		
		 UserDao dao = new UserDao(); 
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
				out.println("window.alert(\"É¾³ý³É¹¦!\");");
				out.println("window.location.href='StudentManageServlet?id=" + id + "';");
				out.println("</script>");
			} else {
				out.println("<script type='text/javascript'>");
				out.println("window.alert(\"É¾³ýÊ§°Ü!!\");");
				out.println("window.location.href='StudentManageServlet?id=" + id + "';");
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
