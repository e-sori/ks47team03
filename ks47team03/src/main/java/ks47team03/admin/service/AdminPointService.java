package ks47team03.admin.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ks47team03.admin.dto.AdminPoint;
import ks47team03.admin.mapper.AdminCommonMapper;
import ks47team03.admin.mapper.AdminPointMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminPointService {
	private final AdminPointMapper adminPointMapper;
	private final AdminCommonMapper adminCommonMapper;
	
	public AdminPointService(AdminPointMapper adminPointMapper, AdminCommonMapper adminCommonMapper) {
		this.adminPointMapper = adminPointMapper;
		this.adminCommonMapper = adminCommonMapper;
	}
	
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
	
	// 포인트 관련기준 조회 
	public Map<String, Object> getPointStandardList(String tableId){		
		// adminPointMapper에서 return 값 받아오기	
		String tableDbName = null;
		Map<String,Object> paramMap = new LinkedHashMap<String,Object>();	
		List<Map<String,Object>> pointStandardList = null;	
		
		if(tableId.equals("pills-max"))	{
			tableDbName = "day_maximum_count";	
			pointStandardList = adminPointMapper.getPointMaxCountStandard("point");	
		}else if(tableId.equals("pills-expire")) {
			tableDbName = "point_expire_standard";	
			pointStandardList = adminPointMapper.getPointExpireStandard();			
		}else if(tableId.equals("pills-save")) {
			tableDbName = "point_save_standard";
			pointStandardList = adminPointMapper.getPointSaveStandard();
		}else if(tableId.equals("pills-refund")) {
			tableDbName = "point_refund_standard";
			pointStandardList = adminPointMapper.getPointRefundStandard();
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

