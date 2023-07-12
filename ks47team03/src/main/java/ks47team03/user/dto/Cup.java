package ks47team03.user.dto;

public class Cup {
	private String userId;
	private String userName;
	private String userNick;
	private String userBarcode;
	private String rentalDateTime;
	private String returnDateTime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getUserBarcode() {
		return userBarcode;
	}
	public void setUserBarcode(String userBarcode) {
		this.userBarcode = userBarcode;
	}
	public String getRentalDateTime() {
		return rentalDateTime;
	}
	public void setRentalDateTime(String rentalDateTime) {
		this.rentalDateTime = rentalDateTime;
	}
	public String getReturnDateTime() {
		return returnDateTime;
	}
	public void setReturnDateTime(String returnDateTime) {
		this.returnDateTime = returnDateTime;
	}
	
	
	private String cupQR;
	private String staticCode;
	private String setDateTime;
	private String adminName;
	private String upDateTime;
	public String getCupQR() {
		return cupQR;
	}
	public void setCupQR(String cupQR) {
		this.cupQR = cupQR;
	}
	public String getStaticCode() {
		return staticCode;
	}
	public void setStaticCode(String staticCode) {
		this.staticCode = staticCode;
	}
	public String getSetDateTime() {
		return setDateTime;
	}
	public void setSetDateTime(String setDateTime) {
		this.setDateTime = setDateTime;
	}
	public String getAdminName() {
		return adminName;
	}
	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}
	public String getUpDateTime() {
		return upDateTime;
	}
	public void setUpDateTime(String upDateTime) {
		this.upDateTime = upDateTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Cup [userId=");
		builder.append(userId);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userNick=");
		builder.append(userNick);
		builder.append(", userBarcode=");
		builder.append(userBarcode);
		builder.append(", rentalDateTime=");
		builder.append(rentalDateTime);
		builder.append(", returnDateTime=");
		builder.append(returnDateTime);
		builder.append(", cupQR=");
		builder.append(cupQR);
		builder.append(", staticCode=");
		builder.append(staticCode);
		builder.append(", setDateTime=");
		builder.append(setDateTime);
		builder.append(", adminName=");
		builder.append(adminName);
		builder.append(", upDateTime=");
		builder.append(upDateTime);
		builder.append("]");
		return builder.toString();
	}
	
	
}
