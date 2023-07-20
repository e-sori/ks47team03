package ks47team03.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


import ks47team03.admin.mapper.AdminDepositMapper;

@Service
public class AdminDepositService {
 
private static final Logger log = LoggerFactory.getLogger(AdminCommonService.class);
	
	private final AdminDepositMapper adminDepositMapper;
	
	public AdminDepositService(AdminDepositMapper adminDepositMapper) {
		this.adminDepositMapper = adminDepositMapper;
	}
	
	//회원 보증금 환급 부분
	public Map<String,Object> getDepositRefundList(int currentPage) {
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		double rowsCount = adminDepositMapper.getDepositRefundListCount();
		
		int lastPage = (int) Math.ceil(rowsCount/rowPerPage);
		//Math.ceil 올림 처리
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
		

		List<Map<String,Object>> depositRefundList = adminDepositMapper.getDepositRefundList(paramMap);
		log.info("전회 회원 보증금 목록:{}",depositRefundList);

		//controller에 전달
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("depositRefundList", depositRefundList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
	}
	
	
	
	
	
	//회원 보증금 관리 부분
	
	public Map<String,Object> getDepositManageList(int currentPage) {
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		double rowsCount = adminDepositMapper.getDepositManageListCount();
		
		int lastPage = (int) Math.ceil(rowsCount/rowPerPage);
		//Math.ceil 올림 처리
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
		

		List<Map<String,Object>> depositManageList = adminDepositMapper.getDepositManageList(paramMap);
		log.info("전회 회원 보증금 목록:{}",depositManageList);

		//controller에 전달
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("depositManageList", depositManageList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
	}
	
	
	//보증금 결제 관리
	public Map<String,Object> getDepositPayList(int currentPage) {
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		double rowsCount = adminDepositMapper.getDepositPayListCount();
		
		int lastPage = (int) Math.ceil(rowsCount/rowPerPage);
		//Math.ceil 올림 처리
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
		

		List<Map<String,Object>> depositPayList = adminDepositMapper.getDepositPayList(paramMap);
		log.info("전체 회원 목록:{}",depositPayList);
	
		paramMap.clear(); 
		paramMap.put("lastPage", lastPage);
		paramMap.put("depositPayList", depositPayList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
	}
	
	
// 보증금 기준 관리 	
	public Map<String,Object> getDepositStandardList(int currentPage) {
		//보여질 행의 갯수
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		//마지막 페이지 계산 
		//1. 보여질 테이블의 전체 행의 갯수
		double rowsCount = adminDepositMapper.getDepositStandardListCount();
		//int보다 더 넓은 자료형을 담아 줄 수 있는게 double 타입 int넣으면 소숫점 절삭
		// ex) 102/5 =20.4 int로 담을경우 소숫점 절삭되서 20으로 됨
		//2. 마지막 페이지
		int lastPage = (int) Math.ceil(rowsCount/rowPerPage);
		//Math.ceil 올림 처리
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
		

		List<Map<String,Object>> depositStandardList = adminDepositMapper.getDepositStandardList(paramMap);
		log.info("전체 회원 목록:{}",depositStandardList);

		//controller에 전달
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("depositStandardList", depositStandardList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
	}
}
