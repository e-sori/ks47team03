package ks47team03.admin.dto;

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
	
	public String getGradeStandardCode() {
		return gradeStandardCode;
	}
	public void setGradeStandardCode(String gradeStandardCode) {
		this.gradeStandardCode = gradeStandardCode;
	}
	public String getPointSaveUseTypeCode() {
		return pointSaveUseTypeCode;
	}
	public void setPointSaveUseTypeCode(String pointSaveUseTypeCode) {
		this.pointSaveUseTypeCode = pointSaveUseTypeCode;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	private String adminPw;
	
	public String getFormName() {
		return formName;
	}
	public void setFormName(String formName) {
		this.formName = formName;
	}
	public String getNewCode() {
		return newCode;
	}
	public void setNewCode(String newCode) {
		this.newCode = newCode;
	}
	public int getUseMaxCount() {
		return useMaxCount;
	}
	public void setUseMaxCount(int useMaxCount) {
		this.useMaxCount = useMaxCount;
	}
	public int getExpirePeriod() {
		return expirePeriod;
	}
	public void setExpirePeriod(int expirePeriod) {
		this.expirePeriod = expirePeriod;
	}
	public String getPointType() {
		return pointType;
	}
	public void setPointType(String pointType) {
		this.pointType = pointType;
	}
	public String getGradeName() {
		return gradeName;
	}
	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}
	public int getGradePoint() {
		return gradePoint;
	}
	public void setGradePoint(int gradePoint) {
		this.gradePoint = gradePoint;
	}
	public int getUsePoint() {
		return usePoint;
	}
	public void setUsePoint(int usePoint) {
		this.usePoint = usePoint;
	}
	public int getMinRefundPoint() {
		return minRefundPoint;
	}
	public void setMinRefundPoint(int minRefundPoint) {
		this.minRefundPoint = minRefundPoint;
	}
	public int getRefundUnit() {
		return refundUnit;
	}
	public void setRefundUnit(int refundUnit) {
		this.refundUnit = refundUnit;
	}
	public int getRefundFeeRate() {
		return refundFeeRate;
	}
	public void setRefundFeeRate(int refundFeeRate) {
		this.refundFeeRate = refundFeeRate;
	}
	public String getCodeUse() {
		return codeUse;
	}
	public void setCodeUse(String codeUse) {
		this.codeUse = codeUse;
	}
	public String getAdminPw() {
		return adminPw;
	}
	public void setAdminPw(String adminPw) {
		this.adminPw = adminPw;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("AdminPoint [formName=");
		builder.append(formName);
		builder.append(", newCode=");
		builder.append(newCode);
		builder.append(", useMaxCount=");
		builder.append(useMaxCount);
		builder.append(", expirePeriod=");
		builder.append(expirePeriod);
		builder.append(", pointType=");
		builder.append(pointType);
		builder.append(", gradeName=");
		builder.append(gradeName);
		builder.append(", gradePoint=");
		builder.append(gradePoint);
		builder.append(", usePoint=");
		builder.append(usePoint);
		builder.append(", minRefundPoint=");
		builder.append(minRefundPoint);
		builder.append(", refundUnit=");
		builder.append(refundUnit);
		builder.append(", refundFeeRate=");
		builder.append(refundFeeRate);
		builder.append(", codeUse=");
		builder.append(codeUse);
		builder.append(", adminId=");
		builder.append(adminId);
		builder.append(", gradeStandardCode=");
		builder.append(gradeStandardCode);
		builder.append(", pointSaveUseTypeCode=");
		builder.append(pointSaveUseTypeCode);
		builder.append(", adminPw=");
		builder.append(adminPw);
		builder.append("]");
		return builder.toString();
	}
	
	
	


}
