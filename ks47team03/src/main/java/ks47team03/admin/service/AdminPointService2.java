package ks47team03.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import ks47team03.admin.mapper.AdminPointMapper;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class AdminPointService2 {
	
	private final AdminPointMapper adminPointMapper;
	
	public AdminPointService2(AdminPointMapper adminPointMapper) {
		this.adminPointMapper = adminPointMapper;
	}
	
	/* 5-2 포인트 환급 기준 조회 */
	public Map<String, Object> getPointRefundStandard(int currentPageRefund){
		
		// 한 번에 최대로 보여줄 행의 개수
		int rowPerPage = 10;
		
		// 현재 페이지의 첫 번째 행의 인덱스
		int currentFirstIndex = (currentPageRefund-1) * rowPerPage;		
		
		// 전체 행의 개수
		double rowsCount = adminPointMapper.getPointStandardCount("point_refund_standard");
		
		// 전체 행의 개수를 한 번에 최대로 보여줄 개수로 나눈 값 (전체 행의 마지막 페이지 번호) 
		int lastPageNum = (int) Math.ceil(rowsCount/rowPerPage);
		
		// 페이지 네이션의 첫 번째 페이지 번호
		int startPageNum = 1;
		
		// 페이지 네이션의 마지막 페이지 번호
		int endPageNum = (lastPageNum < 10)? lastPageNum : 10;
		
		// 동적 페이지 구성(7페이지 부터)
		if(currentPageRefund >= 7 && lastPageNum > 10) {
			startPageNum = currentPageRefund - 5;
			endPageNum = currentPageRefund + 4;
			if (endPageNum >= lastPageNum){
				startPageNum = lastPageNum - 9;
				endPageNum = lastPageNum;				
			}
		}
		
		// adminPointMapper의 인수로 전달할 paramMap에 currentFirstIndex,rowPerPage 담기
		Map<String,Object> paramMap = new HashMap<String,Object>();		
		paramMap.put("currentFirstIndex", currentFirstIndex);
		paramMap.put("rowPerPage", rowPerPage);
		
		// adminPointMapper에서 return 값 받아오기
		List<Map<String,Object>> pointRefundStandardList = adminPointMapper.getPointRefundStandard(paramMap);
		log.info("pointRefundStandardList : {}", pointRefundStandardList);
		
		// controller에 전달될 data
		paramMap.clear(); // map 객체 안의 data 초기화
		paramMap.put("pointRefundStandardList", pointRefundStandardList);
		paramMap.put("lastPageNumRefund", lastPageNum);
		paramMap.put("startPageNumRefund", startPageNum);
		paramMap.put("endPageNumRefund", endPageNum);

		
		
		return paramMap;
	};
	
	
	/* 4-2 포인트 적립 기준 조회 */
	public Map<String, Object> getPointSaveStandard(int currentPageSave){
		
		// 한 번에 최대로 보여줄 행의 개수
		int rowPerPage = 10;
		
		// 현재 페이지의 첫 번째 행의 인덱스
		int currentFirstIndex = (currentPageSave-1) * rowPerPage;		
		
		// 전체 행의 개수
		double rowsCount = adminPointMapper.getPointStandardCount("point_save_standard");
		
		// 전체 행의 개수를 한 번에 최대로 보여줄 개수로 나눈 값 (전체 행의 마지막 페이지 번호) 
		int lastPageNum = (int) Math.ceil(rowsCount/rowPerPage);
		
		// 페이지 네이션의 첫 번째 페이지 번호
		int startPageNum = 1;
		
		// 페이지 네이션의 마지막 페이지 번호
		int endPageNum = (lastPageNum < 10)? lastPageNum : 10;
		
		// 동적 페이지 구성(7페이지 부터)
		if(currentPageSave >= 7 && lastPageNum > 10) {
			startPageNum = currentPageSave - 5;
			endPageNum = currentPageSave + 4;
			if (endPageNum >= lastPageNum){
				startPageNum = lastPageNum - 9;
				endPageNum = lastPageNum;				
			}
		}
		
		// adminPointMapper의 인수로 전달할 paramMap에 currentFirstIndex,rowPerPage 담기
		Map<String,Object> paramMap = new HashMap<String,Object>();		
		paramMap.put("currentFirstIndex", currentFirstIndex);
		paramMap.put("rowPerPage", rowPerPage);
		
		// adminPointMapper에서 return 값 받아오기
		List<Map<String,Object>> pointSaveStandardList = adminPointMapper.getPointSaveStandard(paramMap);
		log.info("pointSaveStandardList : {}", pointSaveStandardList);
		
		// controller에 전달될 data
		paramMap.clear(); // map 객체 안의 data 초기화
		paramMap.put("pointSaveStandardList", pointSaveStandardList);
		paramMap.put("lastPageNumSave", lastPageNum);
		paramMap.put("startPageNumSave", startPageNum);
		paramMap.put("endPageNumSave", endPageNum);

		
		
		return paramMap;
	};
	
	/* 3-2 포인트 타입 기준 조회 */
	public Map<String, Object> getPointTypeStandard(int currentPage){
		
		// 한 번에 최대로 보여줄 행의 개수
		int rowPerPage = 10;
		
		// 현재 페이지의 첫 번째 행의 인덱스
		int currentFirstIndex = (currentPage-1) * rowPerPage;		
		
		// 전체 행의 개수
		double rowsCount = adminPointMapper.getPointStandardCount("point_save_use_type");
		
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
		
		// adminPointMapper의 인수로 전달할 paramMap에 currentFirstIndex,rowPerPage 담기
		Map<String,Object> paramMap = new HashMap<String,Object>();		
		paramMap.put("currentFirstIndex", currentFirstIndex);
		paramMap.put("rowPerPage", rowPerPage);
		
		// adminPointMapper에서 return 값 받아오기
		List<Map<String,Object>> pointTypeStandardList = adminPointMapper.getPointTypeStandard(paramMap);
		log.info("pointTypeStandardList : {}", pointTypeStandardList);
		
		// controller에 전달될 data
		paramMap.clear(); // map 객체 안의 data 초기화
		paramMap.put("pointTypeStandardList", pointTypeStandardList);
		paramMap.put("lastPageNum", lastPageNum);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);

		
		
		return paramMap;
	};
	
	
	
	/* 2-2 포인트 만료 기간 기준 조회 */
	public Map<String, Object> getPointExpireStandard(int currentPageExpire){
		
		// 한 번에 최대로 보여줄 행의 개수
		int rowPerPage = 10;
		
		// 현재 페이지의 첫 번째 행의 인덱스
		int currentFirstIndex = (currentPageExpire-1) * rowPerPage;		
		
		// 전체 행의 개수
		double rowsCount = adminPointMapper.getPointStandardCount("point_expire_standard");

		
		// 전체 행의 개수를 한 번에 최대로 보여줄 개수로 나눈 값 (전체 행의 마지막 페이지 번호) 
		int lastPageNum = (int) Math.ceil(rowsCount/rowPerPage);
		
		// 페이지 네이션의 첫 번째 페이지 번호
		int startPageNum = 1;
		
		// 페이지 네이션의 마지막 페이지 번호
		int endPageNum = (lastPageNum < 10)? lastPageNum : 10;
		
		// 동적 페이지 구성(7페이지 부터)
		if(currentPageExpire >= 7 && lastPageNum > 10) {
			startPageNum = currentPageExpire - 5;
			endPageNum = currentPageExpire + 4;
			if (endPageNum >= lastPageNum){
				startPageNum = lastPageNum - 9;
				endPageNum = lastPageNum;				
			}
		}
		
		// adminPointMapper의 인수로 전달할 paramMap에 currentFirstIndex,rowPerPage 담기
		Map<String,Object> paramMap = new HashMap<String,Object>();		
		paramMap.put("currentFirstIndex", currentFirstIndex);
		paramMap.put("rowPerPage", rowPerPage);
		
		// adminPointMapper에서 return 값 받아오기
		List<Map<String,Object>> pointExpireStandardList = adminPointMapper.getPointExpireStandard(paramMap);
		log.info("pointExpireStandardList : {}", pointExpireStandardList);
		
		// controller에 전달될 data
		paramMap.clear(); // map 객체 안의 data 초기화
		paramMap.put("pointExpireStandardList", pointExpireStandardList);
		paramMap.put("lastPageNumExpire", lastPageNum);
		paramMap.put("startPageNumExpire", startPageNum);
		paramMap.put("endPageNumExpire", endPageNum);

		
		
		return paramMap;
	};	
	
	
	/* 1-2 하루 최대 적립 포인트 횟수 기준 조회 */
	public Map<String, Object> getPointMaxCountStandard(int currentPageMax){
		
		// 한 번에 최대로 보여줄 행의 개수
		int rowPerPage = 10;
		
		// 현재 페이지의 첫 번째 행의 인덱스
		int currentFirstIndex = (currentPageMax-1) * rowPerPage;		
		
		// 전체 행의 개수
		double rowsCount = adminPointMapper.getPointStandardCount("day_maximum_count");
		
		// 전체 행의 개수를 한 번에 최대로 보여줄 개수로 나눈 값 (전체 행의 마지막 페이지 번호) 
		int lastPageNum = (int) Math.ceil(rowsCount/rowPerPage);
		
		// 페이지 네이션의 첫 번째 페이지 번호
		int startPageNum = 1;
		
		// 페이지 네이션의 마지막 페이지 번호
		int endPageNum = (lastPageNum < 10)? lastPageNum : 10;
		
		// 동적 페이지 구성(7페이지 부터)
		if(currentPageMax >= 7 && lastPageNum > 10) {
			startPageNum = currentPageMax - 5;
			endPageNum = currentPageMax + 4;
			if (endPageNum >= lastPageNum){
				startPageNum = lastPageNum - 9;
				endPageNum = lastPageNum;				
			}
		}
		
		// adminPointMapper의 인수로 전달할 paramMap에 currentFirstIndex,rowPerPage 담기
		Map<String,Object> paramMap = new HashMap<String,Object>();		
		paramMap.put("currentFirstIndex", currentFirstIndex);
		paramMap.put("rowPerPage", rowPerPage);
		
		// adminPointMapper에서 return 값 받아오기
		List<Map<String,Object>> pointMaxCountStandardList = adminPointMapper.getPointMaxCountStandard(paramMap);
		log.info("pointMaxCountStandardList : {}", pointMaxCountStandardList);
		List<Map<String,Object>> codeUseList = adminPointMapper.getDistinctData("day_maximum_count", "code_use");
		log.info("codeUseList : {}", codeUseList);
		
		// controller에 전달될 data
		paramMap.clear(); // map 객체 안의 data 초기화
		paramMap.put("pointMaxCountStandardList", pointMaxCountStandardList);
		paramMap.put("lastPageNumMax", lastPageNum);
		paramMap.put("startPageNumMax", startPageNum);
		paramMap.put("endPageNumMax", endPageNum);
		paramMap.put("codeUseList", codeUseList);

		
		
		return paramMap;
	};
}
