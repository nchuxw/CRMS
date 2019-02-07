package bean;

public class ComputerBean implements Comparable<ComputerBean> {
	
	private String comId;
	private String roomId;
	private String comNo;
	private String satus;
	private int layoutX;
	private int layoutY;
	
	public static final String USABLE="可使用";
	public static final String UNUSE="不可用";
	public static final String SUSING="学生使用中";
	public static final String CUSING="班级使用中";
	public static final String REPAIR="维修中";

	public ComputerBean() {
		super();
	}
	
	public ComputerBean(String roomId, String comNo, int layoutX, int layoutY) {
		super();
		this.roomId = roomId;
		this.comNo = comNo;
		this.layoutX = layoutX;
		this.layoutY = layoutY;
	}
	
	public ComputerBean(String comId, String roomId, String comNo, String satus, int layoutX, int layoutY) {
		super();
		this.comId = comId;
		this.roomId = roomId;
		this.comNo = comNo;
		this.satus = satus;
		this.layoutX = layoutX;
		this.layoutY = layoutY;
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

	public String getComNo() {
		return comNo;
	}

	public void setComNo(String comNo) {
		this.comNo = comNo;
	}
	
	public String getSatus() {
		return satus;
	}

	public void setSatus(String satus) {
		this.satus = satus;
	}

	public int getLayoutX() {
		return layoutX;
	}

	public void setLayoutX(int layoutX) {
		this.layoutX = layoutX;
	}

	public int getLayoutY() {
		return layoutY;
	}

	public void setLayoutY(int layoutY) {
		this.layoutY = layoutY;
	}

	@Override
	public int compareTo(ComputerBean o) {
		if(this.layoutY > o.layoutY || (this.layoutY == o.layoutY && this.layoutX > o.layoutX)) {
			return 1;
		}
		else if(this.layoutY < o.layoutY || (this.layoutY == o.layoutY && this.layoutX < o.layoutX)) {
			return -1;
		}
		return 0;
	}
	
	
	
}
