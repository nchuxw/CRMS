package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.ComputerRoomDao;

/**
 * Servlet implementation class RoomTimeManageServlet
 */
@WebServlet("/RoomTimeManageServlet")
public class RoomTimeManageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RoomTimeManageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		int a = 0;
		String roomNo = request.getParameter("room_no");
		String unuseTime = request.getParameter("unuse_time");
		
		ComputerRoomDao dao = new ComputerRoomDao();
		try {
			a = dao.setUnuseTime(roomNo,unuseTime);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			a=-1;
		}
		System.out.println(a);
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		if (a > 0) {
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"设置成功！\");");
			out.println("window.location.href='room_time_manage.jsp';");
			out.println("</script>");
		} else {
			out.println("<script type='text/javascript'>");
			out.println("window.alert(\"设置出错！\");");
			out.println("window.location.href='room_time_manage.jsp';");
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
