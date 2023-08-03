package ks47team03.user.dto;

public class Account {
	private String userBankCode;
	private String userId;
	private String accountUserName;
	private String bankName;
	private String accountNumber;
	
	public String getUserBankCode() {
		return userBankCode;
	}
	public void setUserBankCode(String userBankCode) {
		this.userBankCode = userBankCode;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getAccountUserName() {
		return accountUserName;
	}
	public void setAccountUserName(String accountUserName) {
		this.accountUserName = accountUserName;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Account [userBankCode=");
		builder.append(userBankCode);
		builder.append(", userId=");
		builder.append(userId);
		builder.append(", accountUserName=");
		builder.append(accountUserName);
		builder.append(", bankName=");
		builder.append(bankName);
		builder.append(", accountNumber=");
		builder.append(accountNumber);
		builder.append("]");
		return builder.toString();
	}
	
	
}
