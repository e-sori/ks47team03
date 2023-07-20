package ks47team03.user.dto;

public class Point {
	private String pointPastNowHistoryCode;
	private String userId;
	private int currentHoldingPoint;
	private String adminId;
	private String upDatetime;
	private String saveUseType;
	
	
	public String getPointPastNowHistoryCode() {
		return pointPastNowHistoryCode;
	}
	public void setPointPastNowHistoryCode(String pointPastNowHistoryCode) {
		this.pointPastNowHistoryCode = pointPastNowHistoryCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public int getCurrentHoldingPoint() {
		return currentHoldingPoint;
	}
	public void setCurrentHoldingPoint(int currentHoldingPoint) {
		this.currentHoldingPoint = currentHoldingPoint;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getUpDatetime() {
		return upDatetime;
	}
	public void setUpDatetime(String upDatetime) {
		this.upDatetime = upDatetime;
	}
	public String getSaveUseType() {
		return saveUseType;
	}
	public void setSaveUseType(String saveUseType) {
		this.saveUseType = saveUseType;
	}
	
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Point [pointPastNowHistoryCode=");
		builder.append(pointPastNowHistoryCode);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", currentHoldingPoint=");
		builder.append(currentHoldingPoint);
		builder.append(", adminId=");
		builder.append(adminId);
		builder.append(", upDatetime=");
		builder.append(upDatetime);
		builder.append(", saveUseType=");
		builder.append(saveUseType);
		builder.append("]");
		return builder.toString();
	}	


}
