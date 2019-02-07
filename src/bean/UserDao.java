package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import bean.UserBean;
import util.DBUtil;
import util.Pager;

public class UserDao {

	public static UserBean loadUser(String userId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "select * from tuser where user_id='" + userId + "'";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			UserBean user = new UserBean();
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
			user.setType(rs.getString("type"));
			user.setName(rs.getString("name"));
			user.setUserNo(rs.getString("user_no"));
			user.setClassId(rs.getString("class_id"));
			user.setDepartment(rs.getString("department"));
			DBUtil.freeConnection(con);
			return user;
		}
		DBUtil.freeConnection(con);
		return null;
	}

	public int addUserInfo(UserBean bean) throws SQLException {
		int result = 0;
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		if (con != null) {
			String sql = "insert into tuser(password,type,user_no,name,class_id,department) values (?,?,?,?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, bean.getPassword());
			pstmt.setString(2, bean.getType());
			pstmt.setString(3, bean.getUserNo());
			pstmt.setString(4, bean.getName());
			pstmt.setString(5, bean.getClassId());
			pstmt.setString(6, bean.getDepartment());
			result = pstmt.executeUpdate();
		} else {
			result = -1;
		}
		return result;

	}

	public int update(UserBean bean)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		int result = 0;
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		// System.out.println("dao:"+userId);
		if (conn != null) {
			String sql = "update tuser set password = ?, type = ?, user_no = ?, name = ? ,class_id=?,department=? where user_id = ?";
			System.out.println(sql);
			System.out.println("dao:" + bean.getUserId());
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bean.getPassword());
			pstmt.setString(2, bean.getType());
			pstmt.setString(3, bean.getUserNo());
			pstmt.setString(4, bean.getName());
			pstmt.setString(5, bean.getClassId());
			pstmt.setString(6, bean.getDepartment());
			pstmt.setString(7, bean.getUserId());
			result = pstmt.executeUpdate();
			// System.out.println("result:" + result);
		} else {
			result = -1;
		}
		DBUtil.freeConnection(conn);
		return result;
	}

	public int delete(String id)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		int result = 0;
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");

		if (conn != null) {
			String sql = "delete from tuser  where user_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			result = pstmt.executeUpdate();
		} else {
			result = -1;
		}
		DBUtil.freeConnection(conn);
		return result;
	}

	public int deletes(String[] id)
			throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		int result = 0;
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");

		if (conn != null) {
			String sql = "delete from tuser where user_id in(";
			for (String uid : id) {
				sql += uid + ",";
			}
			sql = sql.substring(0, sql.length() - 1);
			sql += ")";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} else {
			result = -1;
		}
		DBUtil.freeConnection(conn);
		return result;
	}

	public List<UserBean> listStudent(String userNo, String name, String id, Pager pager) throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		if (conn != null) {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select count(*) from tuser";
			String whereSql = " where type='student' ";
			if (id != null && !"".equals(id))
				whereSql += " and class_id =" + Integer.parseInt(id);
			if (userNo != null && !"".equals(userNo))
				whereSql += " and user_no ='" + userNo + "'";
			if (name != null && !"".equals(name))
				whereSql += " and name like '%" + name + "%'";

			// 先计算总记录数
			ResultSet rs = stmt.executeQuery(sql + whereSql);
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			rs.close();
			pager.setTotalRecord(totalCount);

			sql = "select * from tuser where user_id in (select top " + pager.getCurPage() * pager.getPageSize()
					+ " user_id from tuser " + whereSql + ") and user_id not in (";
			sql += "   select top " + (pager.getCurPage() - 1) * pager.getPageSize() + "   user_id from tuser "
					+ whereSql;
			sql += ")";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserBean user = new UserBean();
				user.setUserId(rs.getString("user_id"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getString("type"));
				user.setName(rs.getString("name"));
				user.setUserNo(rs.getString("user_no"));
				user.setClassId(rs.getString("class_id"));
				user.setDepartment(rs.getString("department"));
				list.add(user);
			}
			rs.close();
			stmt.close();
		}
		return list;
	}

	public List<UserBean> listRoomadmin(String userNo, String name, Pager pager) throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		if (conn != null) {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select count(*) from tuser";
			String whereSql = " where type='comrmer' ";

			if (userNo != null && !"".equals(userNo))
				whereSql += " and user_no ='" + userNo + "'";
			if (name != null && !"".equals(name))
				whereSql += " and name like '%" + name + "%'";

			// 先计算总记录数
			ResultSet rs = stmt.executeQuery(sql + whereSql);
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			rs.close();
			pager.setTotalRecord(totalCount);

			sql = "select * from tuser where user_id in (select top " + pager.getCurPage() * pager.getPageSize()
					+ " user_id from tuser " + whereSql + ") and user_id not in (";
			sql += "   select top " + (pager.getCurPage() - 1) * pager.getPageSize() + "   user_id from tuser "
					+ whereSql;
			sql += ")";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserBean user = new UserBean();
				user.setUserId(rs.getString("user_id"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getString("type"));
				user.setName(rs.getString("name"));
				user.setUserNo(rs.getString("user_no"));
				user.setClassId(rs.getString("class_id"));
				user.setDepartment(rs.getString("department"));
				list.add(user);
			}
			rs.close();
			stmt.close();
		}
		return list;
	}

	public List<UserBean> listUser(String userNo, String name, Pager pager) throws SQLException {
		List<UserBean> list = new ArrayList<UserBean>();
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		if (conn != null) {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select count(*) from tuser";
			String whereSql = " where 1=1 ";
			if (userNo != null && !"".equals(userNo))
				whereSql += " and user_no ='" + userNo + "'";
			if (name != null && !"".equals(name))
				whereSql += " and name like '%" + name + "%'";

			// 先计算总记录数
			ResultSet rs = stmt.executeQuery(sql + whereSql);
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			rs.close();
			pager.setTotalRecord(totalCount);

			sql = "select * from tuser where user_id in (select top " + pager.getCurPage() * pager.getPageSize()
					+ " user_id from tuser " + whereSql + ") and user_id not in (";
			sql += "   select top " + (pager.getCurPage() - 1) * pager.getPageSize() + "   user_id from tuser "
					+ whereSql;
			sql += ")";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserBean user = new UserBean();
				user.setUserId(rs.getString("user_id"));
				user.setPassword(rs.getString("password"));
				user.setType(rs.getString("type"));
				user.setName(rs.getString("name"));
				user.setUserNo(rs.getString("user_no"));
				user.setClassId(rs.getString("class_id"));
				user.setDepartment(rs.getString("department"));
				list.add(user);
			}
			rs.close();
			stmt.close();
		}
		return list;
	}

	public List<UserUseLog> listUseLog(String room, String userId, String start, String end, Pager pager)
			throws SQLException {
		List<UserUseLog> list = new ArrayList<UserUseLog>();
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		if (conn != null) {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select count(*) a from user_uselog";
			String whereSql = " where 1=1 ";
			if (room != null && !"".equals(room))
				whereSql += " and user_uselog.com_id in (select computer.com_id from computer where room_id in (select computer_room.room_id from computer_room where name  = '" + room
						+ "'))";
			if (userId != null && !"".equals(userId))
				whereSql += " and user_id in (select user_id from tuser where user_no ='" + userId + "')";
			if (start != null && end != null && !"".equals(start) && !"".equals(end)) {
				whereSql += "and start_time between '" + start + "' and '" + end + "'";
			}

			// 先计算总记录数
			ResultSet rs = stmt.executeQuery(sql + whereSql);
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			rs.close();
			pager.setTotalRecord(totalCount);

			sql = "select computer_room.name,computer.com_no,user_no,start_time,end_time from computer_room,computer,tuser,user_uselog where user_uselog.user_id = tuser.user_id and user_uselog.room_id = computer_room.room_id and user_uselog.com_id = computer.com_id and user_uselog.user_id in (select top "
					+ pager.getCurPage() * pager.getPageSize() + " user_id from user_uselog " + whereSql
					+ ") and user_uselog.user_id not in (";
			sql += "   select top " + (pager.getCurPage() - 1) * pager.getPageSize() + "   user_id from user_uselog "
					+ whereSql;
			sql += ")";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserUseLog user = new UserUseLog();
				user.setRoomId(rs.getString("name"));
				user.setUserId(rs.getString("user_no"));
				user.setComId(rs.getString("com_no"));
				user.setStartTime(rs.getString("start_time"));
				user.setEndTime(rs.getString("end_time"));
				list.add(user);
			}
			rs.close();
			stmt.close();
		}
		return list;
	}

	public List<UserUseLog> listUseLog(String room, String userId, String start, String end) throws SQLException {
		List<UserUseLog> list = new ArrayList<UserUseLog>();
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		if (conn != null) {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select computer.room_id,user_uselog.com_id,user_id,start_time,end_time from user_uselog,computer where user_uselog.com_id = computer.com_id ";

			if (room != null && !"".equals(room)) {
				sql += " and user_uselog.com_id in (select computer.com_id from computer where room_id = " + room + ")";
			}

			if (userId != null && !"".equals(userId)) {
				sql += " and user_id =" + userId;
			}
			if (start != null && end != null && !"".equals(start) && !"".equals(end)) {
				sql += "and start_time between '" + start + "' and '" + end + "'";
			}
			System.out.println(sql);
			// 先计算总记录数
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				UserUseLog user = new UserUseLog();
				user.setUserId(rs.getString("user_id"));
				user.setComId(rs.getString("com_id"));
				user.setStartTime(rs.getString("start_time"));
				user.setEndTime(rs.getString("end_time"));
				list.add(user);
			}
			rs.close();
			stmt.close();
		}
		return list;
	}

	public List<CountByRoomInfo> listByRoomInfo(String room, String start, String end) throws SQLException {
		List<CountByRoomInfo> list = new ArrayList<CountByRoomInfo>();
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		if (conn != null) {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select computer.room_id,com_no,count(user_uselog.com_id) count \r\n"
					+ "from computer left join user_uselog on computer.com_id=user_uselog.com_id where 1=1 ";

			if (room != null && !"".equals(room)) {
				sql += " and computer.com_id in (select computer.com_id from computer where room_id = " + room + ")";
			}
			if (start != null && end != null && !"".equals(start) && !"".equals(end)) {
				sql += "and start_time between '" + start + "' and '" + end + "'";
			}
			sql += " group by computer.room_id,com_no";

			// 先计算总记录数
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CountByRoomInfo info = new CountByRoomInfo();
				info.setRoomId(rs.getString("room_id"));
				info.setComId(rs.getString("com_no"));
				info.setCount(rs.getInt("count"));
				list.add(info);
			}
			rs.close();
			stmt.close();
		}
		return list;
	}

	public List<CountByClassInfo> listByClassInfo(String cla, String start, String end) throws SQLException {
		List<CountByClassInfo> list = new ArrayList<CountByClassInfo>();
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		if (conn != null) {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select class_id,tuser.user_no,\r\n"
					+ "sum(case when tuser.user_id=user_uselog.user_id then 1 else 0 end) count from tuser left join user_uselog\r\n"
					+ "on tuser.user_id=user_uselog.user_id  where 1=1";

			if (cla != null && !"".equals(cla)) {
				sql += " and class_id in (select class_id from tuser where class_id = " + cla + ")";
			}
			if (start != null && end != null && !"".equals(start) && !"".equals(end)) {
				sql += "and start_time between '" + start + "' and '" + end + "'";
			}
			sql += " group by class_id,tuser.user_no";

			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				CountByClassInfo info = new CountByClassInfo();
				info.setCla(rs.getString("class_id"));
				info.setUserId(rs.getString("user_no"));
				info.setCount(rs.getInt("count"));
				list.add(info);
			}
			rs.close();
			stmt.close();
		}
		return list;

	}

	public List<ScoreInfo> listScore(String cla) throws SQLException {
		List<ScoreInfo> list = new ArrayList<ScoreInfo>();
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		if (conn != null) {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select tuser.user_no,name,(case when (1>sum(datediff(s,user_uselog.start_time,user_uselog.end_time))/3600)\r\n"
					+ "then\r\n"
					+ "CONCAT(FLOOR((sum(datediff(s,user_uselog.start_time,user_uselog.end_time))%3600)/60), '分',((sum(datediff(s,user_uselog.start_time,user_uselog.end_time))%3600)%60), '秒')\r\n"
					+ "else\r\n"
					+ "CONCAT(FLOOR(sum(datediff(s,user_uselog.start_time,user_uselog.end_time))/3600),'时',FLOOR((sum(datediff(s,user_uselog.start_time,user_uselog.end_time))%3600)/60), '分',((sum(datediff(s,user_uselog.start_time,user_uselog.end_time))%3600)%60), '秒')\r\n"
					+ "end ) AS time \r\n"
					+ "from user_uselog right join  tuser on user_uselog.user_id= tuser.user_id where 1=1";

			if (cla != null && !"".equals(cla)) {
				sql += " and tuser.user_id in (select user_id from tuser where class_id = '" + cla + "')";
			}
			sql += "group by tuser.user_no,name";
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()) {
				ScoreInfo info = new ScoreInfo();
				info.setUserId(rs.getString("user_no"));
				info.setName(rs.getString("name"));
				info.setScore(rs.getString("time"));
				list.add(info);
			}
			rs.close();
			stmt.close();
		}
		return list;
	}

	public static UserBean loadUser(String userNo, String department) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "select * from tuser " + "where user_no='" + userNo + "' and department='" + department + "';";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			UserBean user = new UserBean();
			user.setUserId(rs.getString("user_id"));
			user.setPassword(rs.getString("password"));
			user.setType(rs.getString("type"));
			user.setName(rs.getString("name"));
			user.setUserNo(rs.getString("user_no"));
			user.setClassId(rs.getString("class_id"));
			user.setDepartment(rs.getString("department"));
			DBUtil.freeConnection(con);
			return user;
		}
		DBUtil.freeConnection(con);
		return null;
	}

	public String getGrade(String userId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String grade = "0";
		if (con != null) {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select (case when (1>sum(datediff(s,user_uselog.start_time,user_uselog.end_time))/3600) "
					+ "then CONCAT(FLOOR((sum(datediff(s,user_uselog.start_time,user_uselog.end_time))%3600)/60), '分',((sum(datediff(s,user_uselog.start_time,user_uselog.end_time))%3600)%60), '秒')  "
					+ "else CONCAT(FLOOR(sum(datediff(s,user_uselog.start_time,user_uselog.end_time))/3600),'时',FLOOR((sum(datediff(s,user_uselog.start_time,user_uselog.end_time))%3600)/60), '分',((sum(datediff(s,user_uselog.start_time,user_uselog.end_time))%3600)%60), '秒') "
					+ "end ) AS time from user_uselog where user_id = '" + userId + "'";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				if (rs.getString("time") != null && !"年月日".equals(rs.getString("time"))) {
					grade = rs.getString("time");
				}
			}
		}
		return grade;
	}

	public String getMinScore(String userId) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String minScore = "";
		if (con != null) {
			Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select min_period from tclass where class_id = (select class_id from tuser where user_id = '"
					+ userId + "')";
			ResultSet rs = stmt.executeQuery(sql);
			if (rs.next()) {
				minScore = rs.getString("min_period");
			}
		}
		return minScore;
	}

}
