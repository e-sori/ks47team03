package ks47team03.user.dto;

public class Kiosk {
	private String kioskSerialNum;
	private String partnerCode;
	private String ApplyDateTime;
	private String installedDateTime;
	private String userId;
	private String staticCode;
	private String partnerName;
	private String partnerAddr;
	public String getKioskSerialNum() {
		return kioskSerialNum;
	}
	public void setKioskSerialNum(String kioskSerialNum) {
		this.kioskSerialNum = kioskSerialNum;
	}
	public String getPartnerCode() {
		return partnerCode;
	}
	public void setPartnerCode(String partnerCode) {
		this.partnerCode = partnerCode;
	}
	public String getApplyDateTime() {
		return ApplyDateTime;
	}
	public void setApplyDateTime(String applyDateTime) {
		ApplyDateTime = applyDateTime;
	}
	public String getInstalledDateTime() {
		return installedDateTime;
	}
	public void setInstalledDateTime(String installedDateTime) {
		this.installedDateTime = installedDateTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStaticCode() {
		return staticCode;
	}
	public void setStatiCode(String staticCode) {
		this.staticCode = staticCode;
	}
	public String getPartnerName() {
		return partnerName;
	}
	public void setPartnerName(String partnerName) {
		this.partnerName = partnerName;
	}
	public String getPartnerAddr() {
		return partnerAddr;
	}
	public void setPartnerAddr(String partnerAddr) {
		this.partnerAddr = partnerAddr;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Kiosk [kioskSerialNum=");
		builder.append(kioskSerialNum);
		builder.append(", partnerCode=");
		builder.append(partnerCode);
		builder.append(", ApplyDateTime=");
		builder.append(ApplyDateTime);
		builder.append(", installedDateTime=");
		builder.append(installedDateTime);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", staticCode=");
		builder.append(staticCode);
		builder.append(", partnerName=");
		builder.append(partnerName);
		builder.append(", partnerAddr=");
		builder.append(partnerAddr);
		builder.append("]");
		return builder.toString();
	}

}