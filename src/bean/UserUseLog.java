package bean;

public class UserUseLog {
	
	private String userId;
	private String startTime;
	private String endTime;
	private String comId;
	private String roomId;
	
	public UserUseLog() {
		super();
	}
	
	public UserUseLog(String userId, String startTime, String comId,String roomId) {
		super();
		this.userId = userId;
		this.startTime = startTime;
		this.comId = comId;
		this.roomId = roomId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

	public String getComId() {
		return comId;
	}

	public void setComId(String comId) {
		this.comId = comId;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
		
	}
}
