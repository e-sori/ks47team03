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
	
	// 포인트 관련 기준 조회 
	public Map<String, Object> getPointStandardList(String tableId){		
		// adminPointMapper에서 return 값 받아오기	
		String tableDbName = null;
		Map<String,Object> paramMap = new LinkedHashMap<String,Object>();	
		List<Map<String,Object>> pointStandardList = null;	
		List<Map<String,Object>> pointTypeList = null;	
		List<Map<String,Object>> gradeList = null;	
		
		if(tableId.equals("pills-max"))	{
			tableDbName = "day_maximum_count";	
			pointStandardList = adminPointMapper.getPointMaxCountStandard("point");	
		}else if(tableId.equals("pills-expire")) {
			tableDbName = "point_expire_standard";	
			pointStandardList = adminPointMapper.getPointExpireStandard();			
		}else if(tableId.equals("pills-save")) {
			tableDbName = "point_save_standard";
			pointStandardList = adminPointMapper.getPointSaveStandard();
			gradeList = adminPointMapper.getGradeStandard();
			paramMap.put("gradeList", gradeList);
		}else if(tableId.equals("pills-refund")) {
			tableDbName = "point_refund_standard";
			pointStandardList = adminPointMapper.getPointRefundStandard();
			pointTypeList = adminPointMapper.getPointTypeStandard();
			paramMap.put("pointTypeList", pointTypeList);
		}else {
			tableDbName = "point_save_use_type";	
			pointStandardList = adminPointMapper.getPointTypeStandard();
		}
		
		List<String> codeUseList = adminPointMapper.getDistinctData(tableDbName, "code_use");
		
		paramMap.put("codeUseList", codeUseList);
		paramMap.put("pointStandardList", pointStandardList);
		
		return paramMap;
	};
}	

