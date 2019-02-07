package servlet;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import bean.UserBean;
import bean.UserDao;

@WebServlet("/ReadExcel")
public class ReadExcel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadExcel() {
		super();
		// TODO Auto-generated constructor stub
	}

	void getDate(HttpServletRequest request, HttpServletResponse response) {
		UserBean bean = new UserBean();
		UserDao dao = new UserDao();
		try {
			java.io.File f = new java.io.File(
					request.getSession().getServletContext().getRealPath("") + "\\upload\\user.xls");
			
			InputStream is = new FileInputStream(f);
			Workbook wb = WorkbookFactory.create(is);

			for (int i = 0, len = wb.getNumberOfSheets(); i < len; i++) {
				Sheet sheet = wb.getSheetAt(i);
				for (int j = 1; j <= sheet.getLastRowNum(); j++) {// 令i=1表示除去标题一行
					Row row = sheet.getRow(j);
					if (row == null) {
						return;
					}
					// 读取每一个单元格
					for (int k = 0; k < row.getLastCellNum(); k++) {
						Cell cell = row.getCell(k);
						if (cell == null) {
							return;
						}
						if (k == 0) {
							String str = getValue(cell);
							bean.setUserNo( str);
							continue;
						}
						if (k == 1) {
							String str = getValue(cell);
							bean.setName(str);
							continue;
						}
						if (k == 2) {
							String str = getValue(cell);
							bean.setDepartment(str);
							continue;
						}
						if (k == 3) {
							String str = getValue(cell);
							bean.setType(str);
							continue;
						}
						if (k == 4) {
							String str = getValue(cell);
							bean.setClassId(str);
							continue;
						}
						if (k == 5) {
							String str = getValue(cell);
							bean.setPassword(str);
							continue;
						}
					}
					try {
						response.setContentType("text/html;charset=utf-8");
						PrintWriter out = response.getWriter();
						if (dao.addUserInfo(bean) > 0) { // 插入数据成功
							//response.sendRedirect("FileList");
							out.println("<script type='text/javascript'>");
							out.println("window.alert(\"导入成功！\");");
							out.println("window.location.href='user_load.jsp';");
							out.println("</script>");
						} else { // 插入数据失败！
							//response.sendRedirect("user_load.jsp?f=1");
							out.println("<script type='text/javascript'>");
							out.println("window.alert(\"导入失败！\");");
							out.println("window.location.href='user_load.jsp';");
							out.println("</script>");
						}
					} catch (Exception e) {
						e.printStackTrace();
					}

				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

//**获取单元格值得方法**
	public void init() throws ServletException {
		
	}

	private String getValue(Cell cell) {
		String result = "";

		switch (cell.getCellType()) {
		case Cell.CELL_TYPE_BOOLEAN:
			result = cell.getBooleanCellValue() + "";
			break;
		case Cell.CELL_TYPE_STRING:
			result = cell.getStringCellValue();
			break;
		case Cell.CELL_TYPE_FORMULA:
			result = cell.getCellFormula();
			break;
		case Cell.CELL_TYPE_NUMERIC:
			// 可能是普通数字，也可能是日期
			if (HSSFDateUtil.isCellDateFormatted(cell)) {
				result = DateUtil.getJavaDate(cell.getNumericCellValue()).toString();
			} else {
				result = cell.getNumericCellValue() + "";
			}
			break;
		}
		return result;
	}

}
