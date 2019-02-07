package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.*;
import bean.UserDao;
import util.*;

/**
 * Servlet implementation class EmployeeEditDoServlet
 */
@WebServlet("/RoomadminDeleteServlet")
public class RoomadminDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomadminDeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		UserDao dao = new UserDao();
		int result = 0;
		try {
			result = dao.delete(id);
		}catch (Exception ex) {
			ex.printStackTrace();
			result = -1;
		}
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if (result > 0) {			
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"É¾³ý³É¹¦!\");");
			out.println("window.location.href='RoomadminManageServlet';");
			out.println("</script>");
		} else {
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"É¾³ýÊ§°Ü!!\");");
			out.println("window.location.href='RoomadminManageServlet';");
			out.println("</script>");
		}
	}

}
