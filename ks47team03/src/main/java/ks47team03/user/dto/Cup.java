package ks47team03.user.dto;

public class Cup {

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
