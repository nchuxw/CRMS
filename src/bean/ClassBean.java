package bean;

public class ClassBean {
	
	private String classId;
	private String name;
	private int stuNum;
	private int minPeriod;
	private String department;
	
	public ClassBean() {
		super();
	}

	public ClassBean(String name, int stuNum, String department) {
		super();
		this.name = name;
		this.stuNum = stuNum;
		this.department = department;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getStuNum() {
		return stuNum;
	}

	public void setStuNum(int stuNum) {
		this.stuNum = stuNum;
	}

	public int getMinPeriod() {
		return minPeriod;
	}

	public void setMinPeriod(int minPeriod) {
		this.minPeriod = minPeriod;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}
	
}
