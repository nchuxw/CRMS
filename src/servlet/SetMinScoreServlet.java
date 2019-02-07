package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ClassDao;

/**
 * Servlet implementation class SetMinScoreServlet
 */
@WebServlet("/SetMinScoreServlet")
public class SetMinScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetMinScoreServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		int a = 0;
		request.setCharacterEncoding("UTF-8");
		String cla = request.getParameter("cla");
		String minScore = request.getParameter("min_score");
		ClassDao dao = new ClassDao();
		
		
		try {
			a = dao.setMinScore(cla, minScore);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			a = -1;
		}
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (a > 0) {
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"设置成功！\");");
			out.println("window.location.href='set_min_score.jsp';");
			out.println("</script>");
		} else {
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"设置出错！\");");
			out.println("window.location.href='set_min_score.jsp';");
			out.println("</script>");
		}
	}

}
