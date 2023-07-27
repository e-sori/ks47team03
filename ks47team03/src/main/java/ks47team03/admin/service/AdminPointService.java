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
	
	/* 포인트 관련기준 조회 */
	public Map<String, Object> getPointStandard(int currentPage, String tableId){
		
		// adminPointMapper에서 return 값 받아오기	
		String tableDbName = null;
		Map<String,Object> paramMap = new LinkedHashMap<String,Object>();	
		List<Map<String,Object>> pointStandardList = null;
		List<Map<String,Object>> pointStandardPrint = null;
		
		// 한 번에 최대로 보여줄 행의 개수
		int rowPerPage = 10;
		
		// 현재 페이지의 첫 번째 행의 인덱스
		int currentFirstIndex = (currentPage-1) * rowPerPage;		
		
		paramMap.put("currentFirstIndex", currentFirstIndex);
		paramMap.put("rowPerPage", rowPerPage);		
		paramMap.put("type", "point");		
		
		if(tableId.equals("pills-max"))	{
			tableDbName = "day_maximum_count";
			pointStandardList = adminPointMapper.getPointMaxCountStandard(paramMap);
			paramMap.clear(); 
			paramMap.put("type", "point");		
			pointStandardPrint = adminPointMapper.getPointMaxCountStandard(paramMap);
		}else if(tableId.equals("pills-expire")) {
			tableDbName = "point_expire_standard";
			pointStandardList = adminPointMapper.getPointExpireStandard(paramMap);
			log.info("pointStandardList : {}", pointStandardList);
		}else if(tableId.equals("pills-save")) {
			tableDbName = "point_save_standard";
			pointStandardList = adminPointMapper.getPointSaveStandard(paramMap);
		}else if(tableId.equals("pills-refund")) {
			tableDbName = "point_refund_standard";
			pointStandardList = adminPointMapper.getPointRefundStandard(paramMap);
		}else {
			tableDbName = "point_save_use_type";	
			pointStandardList = adminPointMapper.getPointTypeStandard(paramMap);
		}
		
		// 전체 행의 개수
		double rowsCount = adminPointMapper.getPointStandardCount(tableDbName);
		
		// 전체 행의 개수를 한 번에 최대로 보여줄 개수로 나눈 값 (전체 행의 마지막 페이지 번호) 
		int lastPageNum = (int) Math.ceil(rowsCount/rowPerPage);
		
		// 페이지 네이션의 첫 번째 페이지 번호
		int startPageNum = 1;
		
		// 페이지 네이션의 마지막 페이지 번호
		int endPageNum = (lastPageNum < 10)? lastPageNum : 10;
		
		// 동적 페이지 구성(7페이지 부터)
		if(currentPage >= 7 && lastPageNum > 10) {
			startPageNum = currentPage - 5;
			endPageNum = currentPage + 4;
			if (endPageNum >= lastPageNum){
				startPageNum = lastPageNum - 9;
				endPageNum = lastPageNum;				
			}
		}		
		
		List<String> codeUseList = adminPointMapper.getDistinctData(tableDbName, "code_use");
		
		// controller에 전달될 data
		paramMap.clear(); // map 객체 안의 data 초기화		
		paramMap.put("codeUseList", codeUseList);
		paramMap.put("tableId", tableId);
		paramMap.put("pointStandardList", pointStandardList);
		paramMap.put("pointStandardPrint", pointStandardPrint);
		paramMap.put("lastPageNum", lastPageNum);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);		
		paramMap.put("rowPerPage", rowPerPage);
		
		return paramMap;
	};
}	

