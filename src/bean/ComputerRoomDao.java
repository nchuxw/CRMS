package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class ComputerRoomDao {
	
	public static ComputerRoomBean loadRoom(String roomId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "select * from computer_room where room_id='" + roomId + "'";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			ComputerRoomBean room = new ComputerRoomBean();
			room.setRoomId(rs.getString("room_id"));
			room.setName(rs.getString("name"));
			room.setWidth(rs.getInt("width"));
			room.setHeight(rs.getInt("height"));
			room.setComNum(rs.getInt("com_num"));
			room.setUsableNum(rs.getInt("usable_num"));
			room.setUnuseTime(rs.getString("unuse_time"));
			room.setDepartment(rs.getString("department"));
			room.setConfig(rs.getString("config"));
			room.setSoftware(rs.getString("software"));
			room.setLiabler(rs.getString("liabler"));
			DBUtil.freeConnection(con);
			return room;
		}
		DBUtil.freeConnection(con);
		return null;
	}
	
	public static ComputerRoomBean loadRoom(String name, String department) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "select * from computer_room "
				+ "where name='" + name + "' and department='" + department + "';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			ComputerRoomBean room = new ComputerRoomBean();
			room.setRoomId(rs.getString("room_id"));
			room.setName(rs.getString("name"));
			room.setWidth(rs.getInt("width"));
			room.setHeight(rs.getInt("height"));
			room.setComNum(rs.getInt("com_num"));
			room.setUsableNum(rs.getInt("usable_num"));
			room.setUnuseTime(rs.getString("unuse_time"));
			room.setDepartment(rs.getString("department"));
			room.setConfig(rs.getString("config"));
			room.setSoftware(rs.getString("software"));
			room.setLiabler(rs.getString("liabler"));
			DBUtil.freeConnection(con);
			return room;
		}
		DBUtil.freeConnection(con);
		return null;
	}
	
	public static List<ComputerRoomBean> loadList(String department) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "select * from computer_room where department='" + department + "'";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<ComputerRoomBean> list = new ArrayList<>();
		while(rs.next()) {
			ComputerRoomBean room = new ComputerRoomBean();
			room.setRoomId(rs.getString("room_id"));
			room.setName(rs.getString("name"));
			room.setWidth(rs.getInt("width"));
			room.setHeight(rs.getInt("height"));
			room.setComNum(rs.getInt("com_num"));
			room.setUsableNum(rs.getInt("usable_num"));
			room.setUnuseTime(rs.getString("unuse_time"));
			room.setDepartment(rs.getString("department"));
			room.setConfig(rs.getString("config"));
			room.setSoftware(rs.getString("software"));
			room.setLiabler(rs.getString("liabler"));
			list.add(room);
		}
		DBUtil.freeConnection(con);
		return list;
	}
	
	public static void addRoom(ComputerRoomBean room) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "insert into computer_room"
				+ "(department,name,width,height,config,software,liabler) values ('"
				+ room.getDepartment() + "','"
				+ room.getName() + "','"
				+ room.getWidth() + "','"
				+ room.getHeight() + "','"
				+ room.getConfig() +"','"
				+ room.getSoftware() +"','"
				+ room.getLiabler() + "');";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}
	
	public static void deleteRoom(String roomId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "delete from computer_room where room_id='" + roomId + "'";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}
	
	public static void deleteRoom(String name, String department) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "delete from computer_room "
				+ "where name='" + name + "' and department='" + department + "';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}

	public int setUnuseTime(String roomNo, String unuseTime) throws SQLException{
		int result = 0;
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		if (con != null) {
			String sql = "update computer_room set unuse_time = ? where name = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, unuseTime);
			pstmt.setString(2, roomNo );
			result = pstmt.executeUpdate();
			System.out.println(sql);
		} else {
			result = -1;
		}
		DBUtil.freeConnection(con);
		return result;
	}
	
}
