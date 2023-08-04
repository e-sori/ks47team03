package ks47team03.user.dto;

public class Point {
	private String pointPastNowHistoryCode;
	private String userId;
	private int currentHoldingPoint;
	private String saveUseType;
	private String pointSaveUseType;
	private int passHoldingpoint;
	private String pointSaveUseHistoryCode;
	private int pointSaveUseHistory;
	private String pointSaveUseTypeCode;
	
	public int getPointSaveUseHistory() {
		return pointSaveUseHistory;
	}
	public void setPointSaveUseHistory(int pointSaveUseHistory) {
		this.pointSaveUseHistory = pointSaveUseHistory;
	}
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
	public String getSaveUseType() {
		return saveUseType;
	}
	public void setSaveUseType(String saveUseType) {
		this.saveUseType = saveUseType;
	}
	public String getPointSaveUseType() {
		return pointSaveUseType;
	}
	public void setPointSaveUseType(String pointSaveUseType) {
		this.pointSaveUseType = pointSaveUseType;
	}
	public int getPassHoldingpoint() {
		return passHoldingpoint;
	}
	public void setPassHoldingpoint(int passHoldingpoint) {
		this.passHoldingpoint = passHoldingpoint;
	}
	public String getPointSaveUseHistoryCode() {
		return pointSaveUseHistoryCode;
	}
	public void setPointSaveUseHistoryCode(String pointSaveUseHistoryCode) {
		this.pointSaveUseHistoryCode = pointSaveUseHistoryCode;
	}
	public String getPointSaveUseTypeCode() {
		return pointSaveUseTypeCode;
	}
	public void setPointSaveUseTypeCode(String pointSaveUseTypeCode) {
		this.pointSaveUseTypeCode = pointSaveUseTypeCode;
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
		builder.append(", saveUseType=");
		builder.append(saveUseType);
		builder.append(", pointSaveUseType=");
		builder.append(pointSaveUseType);
		builder.append(", passHoldingpoint=");
		builder.append(passHoldingpoint);
		builder.append(", pointSaveUseHistoryCode=");
		builder.append(pointSaveUseHistoryCode);
		builder.append(", pointSaveUseHistory=");
		builder.append(pointSaveUseHistory);
		builder.append(", pointSaveUseTypeCode=");
		builder.append(pointSaveUseTypeCode);
		builder.append("]");
		return builder.toString();
	}

}
