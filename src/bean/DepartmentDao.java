package bean;

import java.util.ArrayList;
import java.util.List;

import util.DateUtil;

public class DepartmentDao {
	
	public static boolean isUsable(String unUse) {
		
		List<Integer> list = getIntList(unUse);
		
		String [][]schedul = new DepartmentBean("xxx").getSchedule();
		String time = DateUtil.getNowTimeStr();
		for(int i = 0; i < list.size(); i++) {
			if(time.compareTo(schedul[list.get(i)][0]) >= 0 && 
					time.compareTo(schedul[list.get(i)][1]) < 0) {
				return false;
			}
		}
		return true;
	}
	
	public static List<Integer> getIntList(String str) {
		List<Integer> list = new ArrayList<>();
		
		if(str == null) {
			return list;
		}
		
		int num = 0;
		for(int i = 0; i < str.length(); ) {
			num = num * 10 + (int)(str.charAt(i) - '0');
			i++;
			if(i >= str.length() || str.charAt(i) > '9' || str.charAt(i) < '0' ) {
				list.add(num);
				num = 0;
				i++;
			}
		}
		return list;
	}
	
}
