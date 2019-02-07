package util;

public class DateUtil {

	public static String GetTodayStr() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		java.util.Date today = new java.util.Date();
		
		return sdf.format(today);
	}

	public static String getNowTimeStr() {
		java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("HH:mm:ss");
		java.util.Date today = new java.util.Date();
		
		return sdf.format(today);
	}
		

}
