package ks47team03.admin.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ks47team03.admin.dto.AdminPoint;
import ks47team03.admin.mapper.AdminCommonMapper;
import ks47team03.admin.mapper.AdminPointMapper;
import ks47team03.user.dto.DepositStandard;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AdminPointService {
	private final AdminPointMapper adminPointMapper;
	private final AdminCommonMapper adminCommonMapper;
	
	// 포인트 관련 기준 삭제 (5. 포인트 타입)
    public void deletePointTypeStandard(List<String> codeList) {
        adminPointMapper.deletePointRefundStandardByType(codeList);
        adminPointMapper.deletePointTypeStandard(codeList);
    }
    
    // 포인트 관련 기준 삭제 (4. 포인트 환급 기준)
    public void deletePointRefundStandard(List<String> codeList) {
        adminPointMapper.deletePointRefundStandard(codeList);
    }
    
    // 포인트 관련 기준 삭제 (3. 포인트 적립 기준)
    public void deletePointSaveStandard(List<String> codeList) {
        adminPointMapper.deletePointSaveStandard(codeList);
    }
    
    // 포인트 관련 기준 삭제 (2. 포인트 만료 기간 기준)
    public void deletePointExpireStandard(List<String> codeList) {
        adminPointMapper.deletePointExpireStandard(codeList);
    }
	
	// 포인트 관련 기준 삭제 (1. 하루 최대 적립 포인트 횟수 기준)
	public void deletePointMaxStandard(List<String> codeList) {
	    adminPointMapper.deletePointMaxStandard(codeList);
	}
	
	// 포인트 관련 기준 수정 (5. 포인트 타입)
    public void modifyPointTypeStandard(AdminPoint adminPoint) {
        adminPointMapper.modifyPointTypeStandard(adminPoint);
    }
    
    // 포인트 관련 기준 수정 (4. 포인트 환급 기준)
    public void modifyPointRefundStandard(AdminPoint adminPoint) {
        adminPointMapper.modifyPointRefundStandard(adminPoint);
    }
    
    // 포인트 관련 기준 수정 (3. 포인트 적립 기준)
    public void modifyPointSaveStandard(AdminPoint adminPoint) {
        adminPointMapper.modifyPointSaveStandard(adminPoint);
    }
    
    // 포인트 관련 기준 수정 (2. 포인트 만료 기간 기준)
    public void modifyPointExpireStandard(AdminPoint adminPoint) {
        adminPointMapper.modifyPointExpireStandard(adminPoint);
    }
    
	// 포인트 관련 기준 수정 (1. 하루 최대 적립 포인트 횟수 기준)
	public void modifyPointMaxStandard(AdminPoint adminPoint) {
	    adminPointMapper.modifyPointMaxStandard(adminPoint);
	}
	
	// 등록되어 있는 포인트 타입 전부 가져오기
	public List<String> getAllPointType(){
        List<Map<String, Object>> pointTypeStandardResultMap = adminPointMapper.getPointTypeStandard();
        List<String> pointTypeGroup = new ArrayList<String>();
        
        for(Map<String, Object> type : pointTypeStandardResultMap) {
            pointTypeGroup.add((String) type.get("포인트 타입"));
        }
        
        return pointTypeGroup;
	}
	
	// 선택된 포인트 관련 기준 가져오기 (5. 포인트 타입)
    public AdminPoint getPointTypeStandardDetail(String code) {        
        return adminPointMapper.getPointTypeStandardDetail(code);
    }
    
    // 선택된 포인트 관련 기준 가져오기 (4. 포인트 환급 기준)
    public AdminPoint getPointRefundStandardDetail(String code) {
        return adminPointMapper.getPointRefundStandardDetail(code);
    }
    
    // 선택된 포인트 관련 기준 가져오기 (3. 포인트 적립 기준)
    public AdminPoint getPointSaveStandardDetail(String code) {
        return adminPointMapper.getPointSaveStandardDetail(code);
    }
    
    // 선택된 포인트 관련 기준 가져오기 (2. 포인트 만료 기간 기준)
    public AdminPoint getPointExpireStandardDetail(String code) {
        return adminPointMapper.getPointExpireStandardDetail(code);
    }
	
	// 선택된 포인트 관련 기준 가져오기 (1. 하루 최대 적립 포인트 횟수 기준)
	public AdminPoint getPointMaxStandardDetail(String code) {
	    return adminPointMapper.getPointMaxStandardDetail(code);
	}
	
	// 포인트 관련 기준 등록 (5. 포인트 타입)
	public void addPointTypeStandard(AdminPoint adminPoint) {
		adminPointMapper.addPointTypeStandard(adminPoint);
	};
	
	// 포인트 관련 기준 등록 (4. 포인트 환급 기준)
    public void addPointRefundStandard(AdminPoint adminPoint) {
        adminPointMapper.addPointRefundStandard(adminPoint);
    };
    
    // 포인트 관련 기준 등록 (3. 포인트 적립 기준)
    public void addPointSaveStandard(AdminPoint adminPoint) {
        adminPointMapper.addPointSaveStandard(adminPoint);
    };
    
    // 포인트 관련 기준 등록 (2. 포인트 만료 기간 기준)
    public void addPointExpireStandard(AdminPoint adminPoint) {
        adminPointMapper.addPointExpireStandard(adminPoint);
    };
    
    // 포인트 관련 기준 등록 (1. 하루 최대 적립 포인트 횟수 기준)
    public void addPointMaxStandard(AdminPoint adminPoint) {
       adminPointMapper.addPointMaxCountStandard(adminPoint);
    };
    
    // 관리자 비밀번호 일치 검사
    public int checkPw (String insertAdminPw, String adminPw) {
        final int PWCORRESPOND = 1;
        final int PWNOTCORRESPOND = 2;
        
        int checkPwResult = 0;
        
        if(insertAdminPw.equals(adminPw)) {
            checkPwResult = PWCORRESPOND;
        }else {
            checkPwResult = PWNOTCORRESPOND;
        }   
        return checkPwResult;
    }
	
	// 새로운 코드 가져오기
    public String getIncreaseCode(String tableDbName) {                      
        return adminCommonMapper.getIncreaseCode(tableDbName);
    };  
	
	// 새로운 코드 가져오기 (1. 하루 최대 적립 포인트 횟수 기준)
	public String getIncreaseMaxCode() {						
		return adminPointMapper.getPointMaxIncreaseCode();
	};	
	
	// 포인트 관련 기준 조회 (5. 포인트 타입)
	public Map<String, Object> getPointTypeStandard(){           
        Map<String,Object> paramMap = new LinkedHashMap<String,Object>();   
        List<Map<String,Object>> pointStandardList = adminPointMapper.getPointTypeStandard();

        List<String> codeUseList = adminPointMapper.getDistinctData("point_save_use_type", "code_use");
        
        paramMap.put("codeUseList", codeUseList);
        paramMap.put("pointStandardList", pointStandardList);
        
        return paramMap;
    };
	
	// 포인트 관련 기준 조회 (4. 포인트 환급 기준)
	public Map<String, Object> getPointRefundStandard(){           
        Map<String,Object> paramMap = new LinkedHashMap<String,Object>();   
        List<Map<String,Object>> pointStandardList = adminPointMapper.getPointRefundStandard();
        List<Map<String,Object>> pointTypeList = adminPointMapper.getPointTypeStandard();  
        
        List<String> codeUseList = adminPointMapper.getDistinctData("point_refund_standard", "code_use");
        
        paramMap.put("pointTypeList", pointTypeList);
        paramMap.put("codeUseList", codeUseList);
        paramMap.put("pointStandardList", pointStandardList);
        
        return paramMap;
    };
	
	// 포인트 관련 기준 조회 (3. 포인트 적립 기준)
	public Map<String, Object> getPointSaveStandard(){           
        Map<String,Object> paramMap = new LinkedHashMap<String,Object>();   
        List<Map<String,Object>> pointStandardList = adminPointMapper.getPointSaveStandard();
        List<AdminPoint> gradeList = adminPointMapper.getGradeStandard();
        List<String> codeUseList = adminPointMapper.getDistinctData("point_save_standard", "code_use");      
        
        paramMap.put("gradeList", gradeList);
        paramMap.put("codeUseList", codeUseList);
        paramMap.put("pointStandardList", pointStandardList);
        
        return paramMap;
    };
    
	// 포인트 관련 기준 조회 (2. 포인트 만료 기간 기준)
	public Map<String, Object> getPointExpireStandard(){           
        Map<String,Object> paramMap = new LinkedHashMap<String,Object>();   
        List<Map<String,Object>> pointStandardList = adminPointMapper.getPointExpireStandard();          
        List<String> codeUseList = adminPointMapper.getDistinctData("point_expire_standard", "code_use");
        
        paramMap.put("codeUseList", codeUseList);
        paramMap.put("pointStandardList", pointStandardList);
        
        return paramMap;
    };
    
	// 포인트 관련 기준 조회 (1. 하루 최대 적립 포인트 횟수 기준)
	public Map<String, Object> getPointMaxStandard(){			
		Map<String,Object> paramMap = new LinkedHashMap<String,Object>();	
		List<Map<String,Object>> pointStandardList = adminPointMapper.getPointMaxCountStandard("point");	
		List<String> codeUseList = adminPointMapper.getDistinctData("day_maximum_count", "code_use");
		
		final int NOTUSECODE = -1;
		
		boolean isTrue = false;
		for (Map<String,Object> MaxCount : pointStandardList) { 
            if(MaxCount.get("codeUse").equals("사용중")) { 
                int useMaxCount = (int)MaxCount.get("useMaximumCount"); 
                paramMap.put("useMaxCount", useMaxCount); 
                isTrue = true;
                break; 
            }
        }    
		if(!isTrue) {
		    paramMap.put("useMaxCount", NOTUSECODE); 
		}		
		
		paramMap.put("codeUseList", codeUseList);
		paramMap.put("pointStandardList", pointStandardList);
		
		return paramMap;
	};
}	

