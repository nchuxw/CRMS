package bean;

public class UserBean {
	
	private String userId;
	private String password;
	private String type;
	private String name;
	private String userNo;
	private String classId;
	private String department;
	
	public static final String ADMIN = "admin";
	public static final String COMRMER = "comrmer";
	public static final String STUDENT = "student";
	
	public UserBean() {
		super();
	}

	public UserBean(String userNo) {
		super();
		this.userId = userNo;
	}

	public UserBean(String userId, String password, String type, String name, String userNo, String classId, String department) {
		super();
		this.userId = userId;
		this.password = password;
		this.type = type;
		this.name = name;
		this.userNo=userNo;
		this.classId = classId;
		this.department = department;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	

	public String getUserNo() {
		return userNo;
	}

	public void setUserNo(String userNo) {
		this.userNo = userNo;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
}
