package ks47team03.user.dto;


public class DepositStandard {
	private String waitingDepositStandardCode;
	private int waitingDepositPeriod;
	private String setDatetime;
	private String adminId;
	private String upDatetime;
	
	public String getWaitingDepositStandardCode() {
		return waitingDepositStandardCode;
	}
	public void setWaitingDepositStandardCode(String waitingDepositStandardCode) {
		this.waitingDepositStandardCode = waitingDepositStandardCode;
	}
	public int getWaitingDepositPeriod() {
		return waitingDepositPeriod;
	}
	public void setWaitingDepositPeriod(int waitingDepositPeriod) {
		this.waitingDepositPeriod = waitingDepositPeriod;
	}
	public String getSetDatetime() {
		return setDatetime;
	}
	public void setSetDatetime(String setDatetime) {
		this.setDatetime = setDatetime;
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
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("DepositStandard [waitingDepositStandardCode=");
		builder.append(waitingDepositStandardCode);
		builder.append(", waitingDepositPeriod=");
		builder.append(waitingDepositPeriod);
		builder.append(", setDatetime=");
		builder.append(setDatetime);
		builder.append(", adminId=");
		builder.append(adminId);
		builder.append(", upDatetime=");
		builder.append(upDatetime);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
