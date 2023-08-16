package ks47team03.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ks47team03.user.dto.Deposit;
import ks47team03.user.dto.Point;
import ks47team03.user.mapper.UserDepositMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserDepositService {

private final UserDepositMapper userDepositMapper;

public UserDepositService(UserDepositMapper userDepositMapper) {
	this.userDepositMapper = userDepositMapper;
}
public Map<String,Object> getUserDepositManageList(int currentPage) {
	int rowPerPage = 16;
	
	//페이지 계산(시작될 행의 인덱스)
	int startIndex = (currentPage-1)*rowPerPage;
	
	double rowsCount = userDepositMapper.getUserDepositManageListCount();
	
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
	

	List<Map<String,Object>> userDepositManageList = userDepositMapper.getUserDepositManageList(paramMap);
	log.info("전회 회원 보증금 목록:{}",userDepositManageList);

	//controller에 전달
	paramMap.clear(); // map 객체 안의 data초기화
	paramMap.put("lastPage", lastPage);
	paramMap.put("userDepositManageList", userDepositManageList);
	paramMap.put("startPageNum", startPageNum);
	paramMap.put("endPageNum", endPageNum);
	
	return paramMap;
}
public Map<String,Object> getUserDepositPayList(int currentPage) {
	int rowPerPage = 16;
	
	//페이지 계산(시작될 행의 인덱스)
	int startIndex = (currentPage-1)*rowPerPage;
	
	double rowsCount = userDepositMapper.getUserDepositPayListCount();
	
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
	

	List<Map<String,Object>> userDepositPayList = userDepositMapper.getUserDepositPayList(paramMap);
	log.info("전회 회원 보증금 목록:{}",userDepositPayList);

	//controller에 전달
	paramMap.clear(); // map 객체 안의 data초기화
	paramMap.put("lastPage", lastPage);
	paramMap.put("userDepositPayList", userDepositPayList);
	paramMap.put("startPageNum", startPageNum);
	paramMap.put("endPageNum", endPageNum);
	
	return paramMap;
}
	
	public Deposit getUserDeposit(String userId) {
	Deposit userDeposit = userDepositMapper.getUserDeposit(userId);
	
	return userDeposit;


	


	}

}
