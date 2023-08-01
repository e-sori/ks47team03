package ks47team03.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import ks47team03.admin.mapper.AdminKioskMapper;


@Service

public class AdminKioskService {
	
	private static final Logger log = LoggerFactory.getLogger(AdminKioskService.class);
	
	private final AdminKioskMapper adminKioskMapper;
	
	public AdminKioskService(AdminKioskMapper adminKioskMapper) {
		this.adminKioskMapper = adminKioskMapper;
	}
	
	public Map<String,Object> getInstalledKioskListList(int currentPage) {
		int rowPerPage = 16;
		
		// 페이지 계산
		int startIndex = (currentPage-1)*rowPerPage;
		
		double rowsCount = adminKioskMapper.getInstalledKioskListListCount();
		
		int lastPage = (int) Math.ceil(rowsCount/rowPerPage);
		
		// 처음 번호
		int startPageNum = 1;
		
		// 마지막 번호
		int endPageNum = (lastPage < 10)? lastPage : 10;
		
		if(currentPage >= 7 && lastPage > 10) {
        	startPageNum = currentPage - 5;
            endPageNum = currentPage + 4;
            if(endPageNum >= lastPage) {
            	startPageNum = lastPage - 9;
            	endPageNum = lastPage;
	}
}
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("startIndex", startIndex);
		paramMap.put("rowPerPage", rowPerPage);
		log.info("paramMap:{}",paramMap);
		
		List<Map<String,Object>> installedKioskListList = adminKioskMapper.getInstalledKioskListList(paramMap);
		log.info("설치된 무인기기 목록:{}",installedKioskListList);
		
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("installedKioskListList", installedKioskListList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
	}
}
