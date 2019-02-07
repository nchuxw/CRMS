package bean;

public class DepartmentBean {
	private String name;
	private String[][] schedule;
	private int courseNum;
	
	public DepartmentBean() {
		super();
	}

	public DepartmentBean(String name) {
		super();
		this.name = name;
		this.courseNum = 12;
		this.schedule = new String[20][2];
		
		this.schedule[0][0] = "00:00:01";
		this.schedule[0][1] = "00:00:00";
		
		this.schedule[1][0] = "08:00:00";
		this.schedule[1][1] = "08:45:00";
		
		this.schedule[2][0] = "08:55:00";
		this.schedule[2][1] = "08:40:00";
		
		this.schedule[3][0] = "10:00:00";
		this.schedule[3][1] = "10:45:00";
		
		this.schedule[4][0] = "10:55:00";
		this.schedule[4][1] = "11:40:00";
		
		this.schedule[5][0] = "13:30:00";
		this.schedule[5][1] = "14:15:00";
		
		this.schedule[6][0] = "14:25:00";
		this.schedule[6][1] = "15:10:00";
		
		this.schedule[7][0] = "15:30:00";
		this.schedule[7][1] = "16:15:00";
		
		this.schedule[8][0] = "16:25:00";
		this.schedule[8][1] = "17:10:00";
		
		this.schedule[9][0] = "19:00:00";
		this.schedule[9][1] = "19:45:00";
		
		this.schedule[10][0] = "19:55:00";
		this.schedule[10][1] = "20:40:00";
		
		this.schedule[11][0] = "21:00:00";
		this.schedule[11][1] = "21:45:00";
		
		this.schedule[12][0] = "21:55:00";
		this.schedule[12][1] = "22:40:00";
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[][] getSchedule() {
		return schedule;
	}

	public void setSchedule(String[][] schedule) {
		this.schedule = schedule;
	}

	public int getCourseNum() {
		return courseNum;
	}

	public void setCourseNum(int courseNum) {
		this.courseNum = courseNum;
	}
	
}
