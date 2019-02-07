package servlet;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import bean.UserDao;
import bean.UserUseLog;

/**
 * Servlet implementation class DownExcel
 */
@WebServlet("/DownExcel")
public class DownExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DownExcel() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		@SuppressWarnings("resource")

		String room = request.getParameter("room");
		String userId = request.getParameter("user_id");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		UserDao dao = new UserDao();
		HSSFWorkbook wb = new HSSFWorkbook();
		List<UserUseLog> list = null;
		try {
			list = dao.listUseLog(room, userId, start, end);
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int z = list.size();
		Object[][] datas = new Object[z][4];
		HSSFSheet sheet = wb.createSheet("table"); // ����table������
		for (int i = 0; i < list.size(); i++) {
			//System.out.println("size" + list.size());

			datas[i][0] = list.get(i).getUserId();
			
			datas[i][1] = list.get(i).getComId();
			//System.out.println(datas[i][1].toString());
			datas[i][2] = list.get(i).getStartTime();
			//System.out.println(datas[i][2].toString());
			datas[i][3] = list.get(i).getEndTime();
			//System.out.println(datas[i][3].toString());

		}
		// Object[][] datas = {{bean.getStudentId() ,bean.getCourseId(),
		// bean.getGrade(),bean.getCreatetime()}};
		Object[] datas2 = list.toArray();

		HSSFRow row;
		HSSFCell cell;
		for (int i = 0; i < datas.length; i++) {
			row = sheet.createRow(i);// ���������
			for (int j = 0; j < datas[i].length; j++) {
				cell = row.createCell(j);// ���ݱ���д�����Ԫ��
				cell.setCellValue(String.valueOf(datas[i][j]));
			}
		}
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		wb.write(os);
		byte[] content = os.toByteArray();
		InputStream is = new ByteArrayInputStream(content);
		response.reset();
		response.setContentType("application/vnd.ms-excel;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=table.xls");

		ServletOutputStream out = response.getOutputStream();
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;

		try {
			bis = new BufferedInputStream(is);
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
		wb.write(new FileOutputStream("D:/execltest/table.xls"));
	}

}
