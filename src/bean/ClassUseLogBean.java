package bean;

public class ClassUseLogBean {
	
	private String classId;
	private String startTime;
	private String endTime;
	private String roomId;
	
	public ClassUseLogBean() {
		super();
	}

	public ClassUseLogBean(String classId, String startTime, String roomId) {
		super();
		this.classId = classId;
		this.startTime = startTime;
		this.roomId = roomId;
	}

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}
	
}
