package ks47team03.admin.dto;

public class AdminPoint {
	
	private int dayNum;

	private String dayMaximumCountCode;
	private int useMaximumCount;
	private String codeUse;
	private String setDatetime;
	private String adminId;
	private String upDatetime;
	
	public int getDayNum() {
		return dayNum;
	}
	public void setDayNum(int dayNum) {
		this.dayNum = dayNum;
	}
	
	public String getDayMaximumCountCode() {
		return dayMaximumCountCode;
	}
	public void setDayMaximumCountCode(String dayMaximumCountCode) {
		this.dayMaximumCountCode = dayMaximumCountCode;
	}
	public int getUseMaximumCount() {
		return useMaximumCount;
	}
	public void setUseMaximumCount(int useMaximumCount) {
		this.useMaximumCount = useMaximumCount;
	}
	public String getCodeUse() {
		return codeUse;
	}
	public void setCodeUse(String codeUse) {
		this.codeUse = codeUse;
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
		builder.append("AdminPoint [dayNum=");
		builder.append(dayNum);
		builder.append(", dayMaximumCountCode=");
		builder.append(dayMaximumCountCode);
		builder.append(", useMaximumCount=");
		builder.append(useMaximumCount);
		builder.append(", codeUse=");
		builder.append(codeUse);
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
