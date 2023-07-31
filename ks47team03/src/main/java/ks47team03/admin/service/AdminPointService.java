package ks47team03.admin.service;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import ks47team03.admin.mapper.AdminPointMapper;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AdminPointService {
	
	private final AdminPointMapper adminPointMapper;
	
	public AdminPointService(AdminPointMapper adminPointMapper) {
		this.adminPointMapper = adminPointMapper;
	}
	
	
	// 포인트 관련기준 조회 
	public Map<String, Object> getPointStandard(String tableId){
		
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
		
		// controller에 전달될 data
		paramMap.clear(); // map 객체 안의 data 초기화		
		paramMap.put("codeUseList", codeUseList);
		paramMap.put("tableId", tableId);
		paramMap.put("pointStandardList", pointStandardList);
		log.info("sfsdfsfsfdsf:{}",pointStandardList);
		
		return paramMap;
	};
}	

