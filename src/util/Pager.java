package util;

public class Pager {

	private int totalPage = 0;	// 总页数 
	private int totalRecord = 0; // 总记录数
	private int curPage = 1; 	// 当前页码，从1开始
	private int PageSize = 5; // 每页显示记录数
	
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		this.totalPage = (int) Math.ceil(this.totalRecord * 1.0 / PageSize);
	}
	public int getCurPage() {
		return curPage;
	}
	public void setCurPage(int curPage) {
		this.curPage = curPage;
	}
	public int getPageSize() {
		return PageSize;
	}
	public void setPageSize(int pageSize) {
		PageSize = pageSize;
	}
	
	
}
