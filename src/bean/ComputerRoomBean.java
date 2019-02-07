package bean;

public class ComputerRoomBean {
	
	private String roomId;
	private String name;
	private int width;
	private int height;
	private int comNum;
	private int usableNum;
	private String unuseTime;
	private String department;
	private String config;
	private String software;
	private String liabler;
	
	public ComputerRoomBean() {
		super();
	}
	
	
	
	public ComputerRoomBean(String name, String department, String config, String software, String liabler) {
		super();
		this.name = name;
		this.department = department;
		this.config = config;
		this.software = software;
		this.liabler = liabler;
	}
	
	public ComputerRoomBean(String name, int width, int height, String department, String config, String software,
			String liabler) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.department = department;
		this.config = config;
		this.software = software;
		this.liabler = liabler;
	}

	public ComputerRoomBean(String roomId, String name, int width, int height, int comNum,
			String department) {
		super();
		this.roomId = roomId;
		this.name = name;
		this.width = width;
		this.height = height;
		this.comNum = comNum;
		this.department = department;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getComNum() {
		return comNum;
	}

	public void setComNum(int comNum) {
		this.comNum = comNum;
	}

	public int getUsableNum() {
		return usableNum;
	}

	public void setUsableNum(int usableNum) {
		this.usableNum = usableNum;
	}

	public String getUnuseTime() {
		return unuseTime;
	}

	public void setUnuseTime(String unuseTime) {
		this.unuseTime = unuseTime;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getConfig() {
		return config;
	}

	public void setConfig(String config) {
		this.config = config;
	}

	public String getSoftware() {
		return software;
	}

	public void setSoftware(String software) {
		this.software = software;
	}

	public String getLiabler() {
		return liabler;
	}

	public void setLiabler(String liabler) {
		this.liabler = liabler;
	}
}
