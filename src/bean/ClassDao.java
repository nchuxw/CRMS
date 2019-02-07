package bean;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;
import util.Pager;

public class ClassDao {

	public int setMinScore(String cla, String minScore) throws SQLException{
		
		int result = 0;
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		if (con != null) {
			String sql = "update tclass set min_period = ? where name = ?";
			PreparedStatement pstmt = con.prepareStatement(sql);
			System.out.println(sql);
			pstmt.setString(1, minScore);
			pstmt.setString(2, cla);
			System.out.println(sql);
			result = pstmt.executeUpdate();
		} else {
			result = -1;
		}
		return result;
	}
	public int check(String id) throws SQLException {
		int flag=0;
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		if (conn != null) {
			
			String sql = "select * from tclass where name='"+id+"'";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			if(rs.next()) {
				flag=1;
			}
		}
		return flag;
	}
public ClassBean loadClass(String classId) throws SQLException {
		
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "select * from tclass where class_id='" + Integer.parseInt(classId) + "'";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if (rs.next()) {
			ClassBean clas = new ClassBean();
			clas.setClassId(rs.getString("class_id"));
			clas.setStuNum(Integer.parseInt(rs.getString("stu_num")));
			clas.setMinPeriod(Integer.parseInt(rs.getString("min_period")));
			clas.setName(rs.getString("name"));
			clas.setDepartment(rs.getString("department"));
			DBUtil.freeConnection(con);
			return clas;
		}
		DBUtil.freeConnection(con);
		return null;
	}

	public int addClassInfo(ClassBean bean) throws SQLException {
		int result = 0;
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		if (con != null) {
			if(check(bean.getName())==1) {return -2;}
			String sql = "insert into tclass(name,min_period,department) values (?,?,?)";
			PreparedStatement pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, bean.getName());
			pstmt.setInt(2, bean.getMinPeriod());
			pstmt.setString(3, bean.getDepartment());
			
			result = pstmt.executeUpdate();
		} else {
			result = -1;
		}
		return result;

	}
	
	public int update(ClassBean bean) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		int result = 0;
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		//System.out.println("dao:"+userId);
		if (conn != null) {
			if(check(bean.getName())==1) {return -2;}
			String sql = "update tclass set   min_period = ?, name = ?  where class_id = ?";
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			//pstmt.setInt(1, bean.getStuNum());
			pstmt.setInt(1, bean.getMinPeriod());
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getClassId());
			result = pstmt.executeUpdate();
			//System.out.println("result:" + result);
		} else {
			result = -1;
		}
		DBUtil.freeConnection(conn);
		return result;
	}
	
	public int delete(String id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		int result = 0;
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		
		if (conn != null) {
			String sql = "delete from tclass  where class_id = ?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, Integer.parseInt(id));
			result = pstmt.executeUpdate();
		} else {
			result = -1;
		}
		DBUtil.freeConnection(conn);
		return result;
	}
	public int deletes(String[] id) throws SQLException, InstantiationException, IllegalAccessException, ClassNotFoundException {
		int result = 0;
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		
		if (conn != null) {
			String sql = "delete from tclass where class_id in(";
			 for(String uid:id){
		            sql+=uid+",";
		        }
			 sql = sql.substring(0, sql.length()-1);
		     sql+=")";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
		} else {
			result = -1;
		}
		DBUtil.freeConnection(conn);
		return result;
	}
	public List<ClassBean> listClass(String name, String stuNum,String minPeriod,Pager pager) throws SQLException{
		List<ClassBean> list = new ArrayList<ClassBean>();
		Connection conn = DBUtil.getConnection("CRMS_admin", "admin");
		if (conn != null) {
			Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			String sql = "select count(*) from tclass";
			String whereSql = " where name!='管理员' ";
			
			if (name != null && !"".equals(name))
				whereSql += " and name like '%" + name + "%'";
			if (stuNum != null && !"".equals(stuNum))
				whereSql += " and stu_num =" + Integer.parseInt(stuNum);
			if (minPeriod != null && !"".equals(minPeriod))
				whereSql += " and min_period =" + Integer.parseInt(minPeriod);
			// 先计算总记录数
			ResultSet rs = stmt.executeQuery(sql + whereSql);
			int totalCount = 0;
			if (rs.next()) {
				totalCount = rs.getInt(1);
			}
			rs.close();
			pager.setTotalRecord(totalCount);

			sql = "select * from tclass where class_id in (select top " + pager.getCurPage() * pager.getPageSize()
					+ " class_id from tclass " + whereSql + ") and class_id not in (";
			sql += "   select top " + (pager.getCurPage() - 1) * pager.getPageSize() + "   class_id from tclass "
					+ whereSql;
			sql += ")";
			System.out.println(sql);
			rs = stmt.executeQuery(sql);
			int count=0;
			while (rs.next()) {
				ClassBean clas = new ClassBean();
				clas.setClassId(rs.getString("class_id"));
				clas.setStuNum(Integer.parseInt(rs.getString("stu_num")));
				clas.setMinPeriod(Integer.parseInt(rs.getString("min_period")));
				clas.setName(rs.getString("name"));
				clas.setDepartment(rs.getString("department"));
				list.add(clas);
				count++;
			}
			System.out.println("classdao:"+count);
			rs.close();
			stmt.close();
		}
		return list;
	}
	public static ClassBean loadClass(String name, String department) throws SQLException {
		Connection con = DBUtil.getConnection("CRMS_admin", "admin");
		String sql = "select * from tclass where name='" + name + "' and department='"+ department + "'";
		System.out.println(sql);
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		if(rs.next()) {
			ClassBean cla = new ClassBean();
			cla.setClassId(rs.getString("class_id"));
			cla.setName(rs.getString("name"));
			cla.setStuNum(rs.getInt("stu_num"));
			cla.setMinPeriod(rs.getInt("min_period"));
			cla.setDepartment(rs.getString("department"));
			DBUtil.freeConnection(con);
			return cla;
		}
		DBUtil.freeConnection(con);
		return null;
	}
}
