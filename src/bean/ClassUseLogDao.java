package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.DBUtil;

public class ClassUseLogDao {
	
	public static ClassUseLogBean loadLog(String classId, String startTime ) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "select * from class_uselog "
				+ "where class_id='" + classId + "' and start_time='" + startTime +"';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			ClassUseLogBean claUse = new ClassUseLogBean();
			claUse.setClassId(rs.getString("class_id"));
			claUse.setStartTime(rs.getString("start_time"));
			claUse.setEndTime(rs.getString("end_time"));
			claUse.setRoomId(rs.getString("room_id"));
			DBUtil.freeConnection(con);
			return claUse;
		}
		DBUtil.freeConnection(con);
		return null;
	}
	
	public static void addLog(ClassUseLogBean claUse) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "insert into class_uselog "
				+ "(class_id, start_time, room_id) values('"
				+ claUse.getClassId() + "', '"
				+ claUse.getStartTime() + "', '"
				+ claUse.getRoomId() + "');";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.execute(sql);
	}
	
	public static void classUse(String roomId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "update computer set "
				+ "satus='" + ComputerBean.CUSING + "' "
				+ "where room_id='" + roomId + "' "
				+ "and satus='" + ComputerBean.USABLE +"';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}
	
	public static void classDeplane(String roomId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "update computer set "
				+ "satus='" + ComputerBean.USABLE + "' "
				+ "where room_id='" + roomId + "' "
				+ "and satus='" + ComputerBean.CUSING +"';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}
	
	public static void updateEndTime(String roomId, String endTime) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "update class_uselog set "
				+ "end_time='" + endTime + "' "
				+ "where room_id='" + roomId + "';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}
	
}
