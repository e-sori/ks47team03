package ks47team03.user.dto;

public class Partner {
	private String userId;
	private String KioskSerialNum;
	private int outAmount;
	private String deliveryDesiredDate;
	private String partnerName;
	private String partnerCode;
	private String partnerNumber;
	private String partnerType;
	private String staticCode;
	private String partnerAddr;
	private String upDateTime;
	private double locationLatitude;
	private double locationLongitude;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getKioskSerialNum() {
		return KioskSerialNum;
	}
	public void setKioskSerialNum(String kioskSerialNum) {
		KioskSerialNum = kioskSerialNum;
	}
	public int getOutAmount() {
		return outAmount;
	}
	public void setOutAmount(int outAmount) {
		this.outAmount = outAmount;
	}
	public String getDeliveryDesiredDate() {
		return deliveryDesiredDate;
	}
	public void setDeliveryDesiredDate(String deliveryDesiredDate) {
		this.deliveryDesiredDate = deliveryDesiredDate;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
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
	public String getStaticCode() {
		return staticCode;
	}
	public void setStaticCode(String staticCode) {
		this.staticCode = staticCode;
	}
	public String getPartnerAddr() {
		return partnerAddr;
	}
	public void setPartnerAddr(String partnerAddr) {
		this.partnerAddr = partnerAddr;
	}
	public String getUpDateTime() {
		return upDateTime;
	}
	public void setUpDateTime(String upDateTime) {
		this.upDateTime = upDateTime;
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
		builder.append(", KioskSerialNum=");
		builder.append(KioskSerialNum);
		builder.append(", outAmount=");
		builder.append(outAmount);
		builder.append(", deliveryDesiredDate=");
		builder.append(deliveryDesiredDate);
		builder.append(", partnerName=");
		builder.append(partnerName);
		builder.append(", partnerCode=");
		builder.append(partnerCode);
		builder.append(", partnerNumber=");
		builder.append(partnerNumber);
		builder.append(", partnerType=");
		builder.append(partnerType);
		builder.append(", staticCode=");
		builder.append(staticCode);
		builder.append(", partnerAddr=");
		builder.append(partnerAddr);
		builder.append(", upDateTime=");
		builder.append(upDateTime);
		builder.append(", locationLatitude=");
		builder.append(locationLatitude);
		builder.append(", locationLongitude=");
		builder.append(locationLongitude);
		builder.append("]");
		return builder.toString();
	}
	
	

}
