package ks47team03.user.dto;

public class UserGrade {
	private String gradeManagecode;
	private String gradeStandardcode;
	private String pastGradename;
	private String nowGradename;
	private String userId;
	private String userUsecount;
	private String userGradechangedatetime;
	private String adminId;
	public String getGradeManagecode() {
		return gradeManagecode;
	}
	public void setGradeManagecode(String gradeManagecode) {
		this.gradeManagecode = gradeManagecode;
	}
	public String getGradeStandardcode() {
		return gradeStandardcode;
	}
	public void setGradeStandardcode(String gradeStandardcode) {
		this.gradeStandardcode = gradeStandardcode;
	}
	public String getPastGradename() {
		return pastGradename;
	}
	public void setPastGradename(String pastGradename) {
		this.pastGradename = pastGradename;
	}
	public String getNowGradename() {
		return nowGradename;
	}
	public void setNowGradename(String nowGradename) {
		this.nowGradename = nowGradename;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserUsecount() {
		return userUsecount;
	}
	public void setUserUsecount(String userUsecount) {
		this.userUsecount = userUsecount;
	}
	public String getUserGradechangedatetime() {
		return userGradechangedatetime;
	}
	public void setUserGradechangedatetime(String userGradechangedatetime) {
		this.userGradechangedatetime = userGradechangedatetime;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("UserGrade [gradeManagecode=");
		builder.append(gradeManagecode);
		builder.append(", gradeStandardcode=");
		builder.append(gradeStandardcode);
		builder.append(", pastGradename=");
		builder.append(pastGradename);
		builder.append(", nowGradename=");
		builder.append(nowGradename);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", userUsecount=");
		builder.append(userUsecount);
		builder.append(", userGradechangedatetime=");
		builder.append(userGradechangedatetime);
		builder.append(", adminId=");
		builder.append(adminId);
		builder.append("]");
		return builder.toString();
	}
}
