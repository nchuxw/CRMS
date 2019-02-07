package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import bean.LoginLogBean;
import util.DBUtil;
import util.DateUtil;

public class LoginLogDao {

	public List<LoginLogBean> showLog() throws SQLException{
		List<LoginLogBean> list = new ArrayList<LoginLogBean>();
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		if (con != null) {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select * from login_log";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				LoginLogBean bean = new LoginLogBean();
				bean.setUserId(rs.getString("user_id"));
				bean.setLoginTime(rs.getString("login_time"));
				bean.setLogoutTime(rs.getString("logout_time"));
				bean.setIpAddress(rs.getString("ip_address"));
				list.add(bean);
			}
			rs.close();
			stmt.close();
		}

		DBUtil.freeConnection(con);
		return list;
	}

	public static void add(String user_id, String localip) throws SQLException{
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		if (con != null) {
			String sql = "insert into login_log(user_id,login_time,logout_time,ip_address) values (?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, user_id);
			pstmt.setString(2,DateUtil.GetTodayStr());
			pstmt.setString(3,null);
			pstmt.setString(4, localip);
			
			pstmt.executeUpdate();
			pstmt.close();
		} 
		con.close();
		
	}

	public void addLogoutTime(String id) throws SQLException{
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		if(con != null) {
			String sql = "update login_log set logout_time = ? where user_id = ? and logout_time is null"; 
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, DateUtil.GetTodayStr());
			pstmt.setString(2, id);
			pstmt.executeUpdate();
			pstmt.close();
		}
		con.close();
		
	}

}
