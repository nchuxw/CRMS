package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class UserLogDao {
	public UserUseLog loadLog(String id) throws SQLException {//
		UserUseLog bean = new UserUseLog();
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		if (con != null) {
			Statement stmt = con.createStatement();
			String sql = "select name,com_id,start_time  from computer_room,user_uselog where computer_room.room_id in (select room_id  from user_uselog where user_id = "
					+ Integer.parseInt(id) + ") and end_time is null";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				bean.setRoomId(rs.getString("name"));
				// bean.setUserId(rs.getString("user_uselog.user_id"));
				bean.setStartTime(rs.getString("start_time"));
				bean.setComId(rs.getString("com_id"));

			}
			rs.close();
			stmt.close();
		}

		DBUtil.freeConnection(con);
		return bean;
	}

	public String loadTime(String id) throws SQLException {
		String total = null;
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		if (con != null) {
			Statement stmt = con.createStatement();
			String sql = "select (case when (1>datediff(s,user_uselog.start_time,getdate())/3600) then CONCAT(FLOOR((datediff(s,user_uselog.start_time,getdate())%3600)/60), '∑÷',((datediff(s,user_uselog.start_time,getdate())%3600)%60), '√Î') else CONCAT(FLOOR(datediff(s,user_uselog.start_time,getdate())/3600),' ±',FLOOR((datediff(s,user_uselog.start_time,getdate())%3600)/60), '∑÷',((datediff(s,user_uselog.start_time,getdate())%3600)%60), '√Î') end ) AS time from user_uselog where user_id="
					+ Integer.parseInt(id);

			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				total = rs.getString("time");
			}
			rs.close();
			stmt.close();

		}
		return total;
	}

	public List<UserUseLog> loadLogTime(String id,String times) throws SQLException {
		List<UserUseLog> list = new ArrayList<UserUseLog>();
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		if (con != null) {
			Statement stmt = con.createStatement();
			String sql = "select name,com_id,start_time,end_time \r\n" + 
					"from computer_room,user_uselog \r\n" + 
					"where computer_room.room_id= user_uselog.room_id and user_id = " +Integer.parseInt(id) + " and start_time between dateadd(D,-"+Integer.parseInt(times)+",getdate()) and getdate()\r\n" + 
					"";

			ResultSet rs = stmt.executeQuery(sql);

			while (rs.next()) {
				UserUseLog bean = new UserUseLog();
				bean.setRoomId(rs.getString("name"));
				bean.setEndTime(rs.getString("end_time"));
				bean.setStartTime(rs.getString("start_time"));
				bean.setComId(rs.getString("com_id"));
				list.add(bean);
			}
			rs.close();
			stmt.close();
		}

		DBUtil.freeConnection(con);
		return list;
	}

	public static void updateEndTime(String comId, String endTime) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "update user_uselog set "
				+ "end_time='" + endTime + "' "
				+ "where com_id='" + comId + "';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}

	public static void addLog(UserUseLog log) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "insert into user_uselog "
				+ "(user_id, com_id,room_id, start_time) values ('"
				+ log.getUserId() + "', '"
				+ log.getComId() + "', '"
				+ log.getRoomId() + "','"
				+ log.getStartTime() + "');";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
	}
}
