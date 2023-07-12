package ks47team03.admin.service;

import java.util.HashMap;
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
	
	
	/* 하루 최대 적립 포인트 횟수 기준 */
	public Map<String, Object> getPointMaxCountStandard(int currentPage){
		
		// 한 번에 최대로 보여줄 행의 개수
		int rowPerPage = 20;
		
		// 현재 페이지의 첫 번째 행의 인덱스
		int currentFirstIndex = (currentPage-1) * rowPerPage;		
		
		// 전체 행의 개수
		double rowsCount = adminPointMapper.getPointMaxCountStandardCount();
		
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
		List<Map<String,Object>> pointMaxCountStandardList = adminPointMapper.getPointMaxCountStandard(paramMap);
		log.info("pointMaxCountStandardList : {}", pointMaxCountStandardList);
		
		// controller에 전달될 data
		paramMap.clear(); // map 객체 안의 data 초기화
		paramMap.put("pointMaxCountStandardList", pointMaxCountStandardList);
		paramMap.put("lastPageNum", lastPageNum);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);

		
		
		return paramMap;
	};
}
