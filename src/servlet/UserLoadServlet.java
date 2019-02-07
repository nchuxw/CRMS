package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
@WebServlet("/UserLoadServlet")
public class UserLoadServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		/* *进行文件的上传： */
		if (type.equals("excToSqlServer")) {
			SmartUpload su = new SmartUpload();
			su.setCharset("UTF-8");
			su.initialize(this.getServletConfig(), request, response);
			// 设定允许上传的文件（通过扩展名限制）
			su.setAllowedFilesList("xls,xlsx");
			try {
				su.upload();
				
			} catch (SmartUploadException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			File file = su.getFiles().getFile(0);
			try {
				String temp = "";
				temp = "/upload/user.xls";
				file.saveAs(temp, SmartUpload.SAVE_VIRTUAL);
				System.out.println("上传成功!");
				// 调用读取文件的方法;**
				ReadExcel es = new ReadExcel();
				es.getDate(request, response);
			} catch (SmartUploadException e) {
				e.printStackTrace();
			}
		}
	}

}