package ks47team03.user.dto;

import lombok.Data;

@Data
public class Deposit {
	private String depositPayHistory;
	private String userId;
	private int chargingAmount;
	private String paymentMethod;
	private String payApplicationDatetime;
	private String waitingDepositStandardCode;
	private int waitingDepositPeriod;
	private String waitingDepositDatetime;
	private String staticCode;
	private String adminId;
	private String depositCompletionDatetime;
	private String depositPayRefundHistoryCode;
	private int pastHoldingDeposit;
	private int depositPayUseHistory;
	private String depositUsingType;
	private int currentHoldingDeposit;
	private String depositRefundHistroyCode;
	private String userName;
	private String accountUserName;
	private String bankName;
	private String accountNumber;
	private int depositRefundApply;
	private int depositRefundResult;
}
