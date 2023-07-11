package ks47team03.user.dto;

public class User {
	private String userId;
	private String userPw;
	private String userName;
	private String userLevel;
	private String userIdcardnumber;
	private String userAddr;
	private String userEmail;
	private String userPhone;
	private String userNick;
	private String userSmscheck;
	private String userEmailcheck;
	private String staticCode;
	private String userJoindatetime;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserPw() {
		return userPw;
	}
	public void setUserPw(String userPw) {
		this.userPw = userPw;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserLevel() {
		return userLevel;
	}
	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}
	public String getUserIdcardnumber() {
		return userIdcardnumber;
	}
	public void setUserIdcardnumber(String userIdcardnumber) {
		this.userIdcardnumber = userIdcardnumber;
	}
	public String getUserAddr() {
		return userAddr;
	}
	public void setUserAddr(String userAddr) {
		this.userAddr = userAddr;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}
	public String getUserNick() {
		return userNick;
	}
	public void setUserNick(String userNick) {
		this.userNick = userNick;
	}
	public String getUserSmscheck() {
		return userSmscheck;
	}
	public void setUserSmscheck(String userSmscheck) {
		this.userSmscheck = userSmscheck;
	}
	public String getUserEmailcheck() {
		return userEmailcheck;
	}
	public void setUserEmailcheck(String userEmailcheck) {
		this.userEmailcheck = userEmailcheck;
	}
	public String getStaticCode() {
		return staticCode;
	}
	public void setStaticCode(String staticCode) {
		this.staticCode = staticCode;
	}
	public String getUserJoindatetime() {
		return userJoindatetime;
	}
	public void setUserJoindatetime(String userJoindatetime) {
		this.userJoindatetime = userJoindatetime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [userId=");
		builder.append(userId);
		builder.append(", userPw=");
		builder.append(userPw);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", userLevel=");
		builder.append(userLevel);
		builder.append(", userIdcardnumber=");
		builder.append(userIdcardnumber);
		builder.append(", userAddr=");
		builder.append(userAddr);
		builder.append(", userEmail=");
		builder.append(userEmail);
		builder.append(", userPhone=");
		builder.append(userPhone);
		builder.append(", userNick=");
		builder.append(userNick);
		builder.append(", userSmscheck=");
		builder.append(userSmscheck);
		builder.append(", userEmailcheck=");
		builder.append(userEmailcheck);
		builder.append(", staticCode=");
		builder.append(staticCode);
		builder.append(", userJoindatetime=");
		builder.append(userJoindatetime);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
