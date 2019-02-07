package bean;

import java.util.Date;

public class LoginLogBean {
	
	private String userId;
	private String loginTime;
	private String logoutTime;
	private String ipAddress;
	
	public LoginLogBean() {
		super();
	}

	public LoginLogBean(String userId, String loginTime, String ipAddress) {
		super();
		this.userId = userId;
		this.loginTime = loginTime;
		this.ipAddress = ipAddress;
	}

	public LoginLogBean(String userId, String loginTime, String logoutTime, String ipAddress) {
		super();
		this.userId = userId;
		this.loginTime = loginTime;
		this.logoutTime = logoutTime;
		this.ipAddress = ipAddress;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getLogoutTime() {
		return logoutTime;
	}

	public void setLogoutTime(String logoutTime) {
		this.logoutTime = logoutTime;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAdress) {
		this.ipAddress = ipAdress;
	}
}
