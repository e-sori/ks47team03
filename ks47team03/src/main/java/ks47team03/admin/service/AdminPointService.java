package ks47team03.admin.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import ks47team03.admin.dto.AdminPoint;
import ks47team03.admin.mapper.AdminCommonMapper;
import ks47team03.admin.mapper.AdminPointMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class AdminPointService {
	private final AdminPointMapper adminPointMapper;
	private final AdminCommonMapper adminCommonMapper;
	
	// 포인트 관련 기준 등록
	public void addPointStandard(AdminPoint adminPoint) {
		
		String formName = adminPoint.getFormName();
		
		if (formName.equals("pointMaxCount")) adminPointMapper.addPointMaxCountStandard(adminPoint);
		else if (formName.equals("pointExpire")) adminPointMapper.addPointExpireStandard(adminPoint);
		else if (formName.equals("pointType")) adminPointMapper.addPointTypeStandard(adminPoint);
		else if (formName.equals("pointSave")) adminPointMapper.addPointSaveStandard(adminPoint);
		else if (formName.equals("pointRefund")) adminPointMapper.addPointRefundStandard(adminPoint);
		
	};
	
	// 새로운 코드 가져오기
	public String getIncreaseCode(String tableDbName) {		
		
		String getNewCode = null;
		
		if(tableDbName.equals("day_maximum_count")) getNewCode = adminPointMapper.getPointMaxIncreaseCode();
		else getNewCode = adminCommonMapper.getIncreaseCode(tableDbName);
		
		return getNewCode;
	};	
	
	// 포인트 관련 기준 조회 (5. 포인트 타입)
	public Map<String, Object> getPointTypeStandard(){           
        Map<String,Object> paramMap = new LinkedHashMap<String,Object>();   
        List<Map<String,Object>> pointStandardList = null;   

        pointStandardList = adminPointMapper.getPointTypeStandard();
        
        List<String> codeUseList = adminPointMapper.getDistinctData("point_save_use_type", "code_use");
        
        paramMap.put("codeUseList", codeUseList);
        paramMap.put("pointStandardList", pointStandardList);
        
        return paramMap;
    };
	
	// 포인트 관련 기준 조회 (4. 포인트 환급 기준)
	public Map<String, Object> getPointRefundStandard(){           
        Map<String,Object> paramMap = new LinkedHashMap<String,Object>();   
        List<Map<String,Object>> pointStandardList = null;  
        List<Map<String,Object>> pointTypeList = null;      

        pointStandardList = adminPointMapper.getPointRefundStandard();
        pointTypeList = adminPointMapper.getPointTypeStandard();
        
        List<String> codeUseList = adminPointMapper.getDistinctData("point_refund_standard", "code_use");
        
        paramMap.put("pointTypeList", pointTypeList);
        paramMap.put("codeUseList", codeUseList);
        paramMap.put("pointStandardList", pointStandardList);
        
        return paramMap;
    };
	
	// 포인트 관련 기준 조회 (3. 포인트 적립 기준)
	public Map<String, Object> getPointSaveStandard(){           
        Map<String,Object> paramMap = new LinkedHashMap<String,Object>();   
        List<Map<String,Object>> pointStandardList = null;  
        List<Map<String,Object>> gradeList = null;          

        pointStandardList = adminPointMapper.getPointSaveStandard();
        gradeList = adminPointMapper.getGradeStandard();
        List<String> codeUseList = adminPointMapper.getDistinctData("point_save_standard", "code_use");      
        
        paramMap.put("gradeList", gradeList);
        paramMap.put("codeUseList", codeUseList);
        paramMap.put("pointStandardList", pointStandardList);
        
        return paramMap;
    };
    
	// 포인트 관련 기준 조회 (2. 포인트 만료 기간 기준)
	public Map<String, Object> getPointExpireStandard(){           
        Map<String,Object> paramMap = new LinkedHashMap<String,Object>();   
        List<Map<String,Object>> pointStandardList = null;  

        pointStandardList = adminPointMapper.getPointExpireStandard();          
        List<String> codeUseList = adminPointMapper.getDistinctData("point_expire_standard", "code_use");
        
        paramMap.put("codeUseList", codeUseList);
        paramMap.put("pointStandardList", pointStandardList);
        
        return paramMap;
    };
    
	// 포인트 관련 기준 조회 (1. 하루 최대 적립 포인트 횟수 기준)
	public Map<String, Object> getPointMaxStandard(){			
		Map<String,Object> paramMap = new LinkedHashMap<String,Object>();	
		List<Map<String,Object>> pointStandardList = null;	
	
		pointStandardList = adminPointMapper.getPointMaxCountStandard("point");	
		List<String> codeUseList = adminPointMapper.getDistinctData("day_maximum_count", "code_use");
		
		for (Map<String,Object> MaxCount : pointStandardList) { 
            if(MaxCount.get("codeUse").equals("사용중")) { 
                int useMaxCount = (int)MaxCount.get("useMaximumCount"); 
                paramMap.put("useMaxCount", useMaxCount); 
                break; 
            } 
        }         
		
		paramMap.put("codeUseList", codeUseList);
		paramMap.put("pointStandardList", pointStandardList);
		
		return paramMap;
	};
}	

