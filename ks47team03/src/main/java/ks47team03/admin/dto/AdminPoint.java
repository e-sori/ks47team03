package ks47team03.admin.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminPoint {
	private String formName;
	private String newCode;
	private int useMaxCount;
	private int expirePeriod;
	private String pointType;
	private String gradeName;
	private int gradePoint;
	private int usePoint;
	private int minRefundPoint;
	private int refundUnit;
	private int refundFeeRate;
	private String codeUse;
	private String adminId;
	private String gradeStandardCode;
	private String pointSaveUseTypeCode;
}
