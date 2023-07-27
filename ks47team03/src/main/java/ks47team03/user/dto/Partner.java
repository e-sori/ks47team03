package ks47team03.user.dto;

public class Partner {
	private String userId;
	private String partnerName;
	private String partnerNumber;
	private String partnerType;
	private String static_code;
	private String partnerAddr;
	private double locationLatitude;
	private double locationLongitude;
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerNumber() {
		return partnerNumber;
	}
	public void setPartnerNumber(String partnerNumber) {
		this.partnerNumber = partnerNumber;
	}
	public String getPartnerType() {
		return partnerType;
	}
	public void setPartnerType(String partnerType) {
		this.partnerType = partnerType;
	}
	public String getStatic_code() {
		return static_code;
	}
	public void setStatic_code(String static_code) {
		this.static_code = static_code;
	}
	public String getPartnerAddr() {
		return partnerAddr;
	}
	public void setPartnerAddr(String partnerAddr) {
		this.partnerAddr = partnerAddr;
	}
	public double getLocationLatitude() {
		return locationLatitude;
	}
	public void setLocationLatitude(double locationLatitude) {
		this.locationLatitude = locationLatitude;
	}
	public double getLocationLongitude() {
		return locationLongitude;
	}
	public void setLocationLongitude(double locationLongitude) {
		this.locationLongitude = locationLongitude;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Partner [userId=");
		builder.append(userId);
		builder.append(", partnerName=");
		builder.append(partnerName);
		builder.append(", partnerNumber=");
		builder.append(partnerNumber);
		builder.append(", partnerType=");
		builder.append(partnerType);
		builder.append(", static_code=");
		builder.append(static_code);
		builder.append(", partnerAddr=");
		builder.append(partnerAddr);
		builder.append(", locationLatitude=");
		builder.append(locationLatitude);
		builder.append(", locationLongitude=");
		builder.append(locationLongitude);
		builder.append("]");
		return builder.toString();
	}
}
