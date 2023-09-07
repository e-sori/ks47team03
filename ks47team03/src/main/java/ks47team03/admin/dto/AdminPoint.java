package ks47team03.admin.dto;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AdminPoint {
    private String dayMaximumCountCode;
    private int useMaximumCount;
    
    private String pointExpireStandardCode;
    private int pointExpire;
    
    private String pointSaveStandardCode;
    private String gradeStandardCode;
    private String gradeName;
    private int gradeAttainPoint;
    private int useSavePoint;
    
    private String pointRefundStandardCode;
    private int minimumRefundStandard;
    private int refundUnit;
    private int refundFeeRate;
    
    private String pointSaveUseTypeCode;
    private String pointSaveUseType;
    private List<String> pointTypeGroup;
      
    private String codeUse;
    private String adminId;
    private LocalDate setDatetime;
    private LocalDate upDatetime;
    
	private String newCode;
	private String insertAdminPw;
	
}
