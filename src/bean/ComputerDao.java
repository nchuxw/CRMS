package bean;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class ComputerDao {
	
	public static ComputerBean loadComputer(String comId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "select * from computer where com_id='" + comId + "'";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			ComputerBean com = new ComputerBean();
			com.setComId(rs.getString("com_id"));
			com.setRoomId(rs.getString("room_id"));
			com.setComNo(rs.getString("com_no"));
			com.setSatus(rs.getString("satus"));
			com.setLayoutX(rs.getInt("layout_x"));
			com.setLayoutY(rs.getInt("layout_y"));
			DBUtil.freeConnection(con);
			return com;
		}
		DBUtil.freeConnection(con);
		return null;
	}
	
	public static List<ComputerBean> loadList(String roomId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "select * from computer where room_id='" + roomId + "'";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		List<ComputerBean> list = new ArrayList<>();
		while(rs.next()) {
			ComputerBean com = new ComputerBean();
			com.setComId(rs.getString("com_id"));
			com.setRoomId(rs.getString("room_id"));
			com.setComNo(rs.getString("com_no"));
			com.setSatus(rs.getString("satus"));
			com.setLayoutX(rs.getInt("layout_x"));
			com.setLayoutY(rs.getInt("layout_y"));
			list.add(com);
		}
		DBUtil.freeConnection(con);
		return list;
	}
	
	public static void addComputer(ComputerBean com) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "insert into computer"
				+ "(room_id,com_no,layout_x,layout_y) values ('"
				+ com.getRoomId() + "','"
				+ com.getComNo() + "','"
				+ com.getLayoutX() + "','"
				+ com.getLayoutY() + "');";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}
	
	public static void updataSatus(String comId, String satus) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "update computer set "
				+ "satus='" + satus + "' "
				+ "where com_id='" + comId + "';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}
	
	public static void updataRoomSatus(String roomId, String satus) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "update computer set "
				+ "satus='" + satus + "' "
				+ "where room_id='" + roomId + "' ";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}
	
	public static void roomUnuse(String roomId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "update computer set "
				+ "satus='" + ComputerBean.UNUSE + "' "
				+ "where room_id='" + roomId + "' and satus!='" + ComputerBean.REPAIR + "';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}
	
	public static void roomUsable(String roomId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "update computer set "
				+ "satus='" + ComputerBean.USABLE + "' "
				+ "where room_id='" + roomId + "' and satus='" + ComputerBean.UNUSE + "';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		stmt.executeUpdate(sql);
		DBUtil.freeConnection(con);
	}

	
}
