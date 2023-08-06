package ks47team03.user.dto;

public class Loghistory {
	private String logHistoryCode;
	private String userId;
	private String loginHistoryDatetime;
	private String logoutHistoryDatetime;
	public String getLogHistoryCode() {
		return logHistoryCode;
	}
	public void setLogHistoryCode(String logHistoryCode) {
		this.logHistoryCode = logHistoryCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getLoginHistoryDatetime() {
		return loginHistoryDatetime;
	}
	public void setLoginHistoryDatetime(String loginHistoryDatetime) {
		this.loginHistoryDatetime = loginHistoryDatetime;
	}
	public String getLogoutHistoryDatetime() {
		return logoutHistoryDatetime;
	}
	public void setLogoutHistoryDatetime(String logoutHistoryDatetime) {
		this.logoutHistoryDatetime = logoutHistoryDatetime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Loghistory [logHistoryCode=");
		builder.append(logHistoryCode);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", loginHistoryDatetime=");
		builder.append(loginHistoryDatetime);
		builder.append(", logoutHistoryDatetime=");
		builder.append(logoutHistoryDatetime);
		builder.append("]");
		return builder.toString();
	}
}
