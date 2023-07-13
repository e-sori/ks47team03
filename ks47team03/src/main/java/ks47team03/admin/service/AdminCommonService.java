package ks47team03.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import ks47team03.user.dto.User;
import ks47team03.admin.mapper.AdminCommonMapper;

@Service
public class AdminCommonService {
	private static final Logger log = LoggerFactory.getLogger(AdminCommonService.class);
	
	private final AdminCommonMapper adminCommonMapper;
	
	public AdminCommonService(AdminCommonMapper adminCommonMapper) {
		this.adminCommonMapper = adminCommonMapper;
	}
	
	@PostConstruct
	public void userServiceInit() {
		System.out.println("userService 객체 생성");
	}
	
	//관리자 아이디 조회
	public List<User> getadminIdList(){
		List<User> adminIdList = adminCommonMapper.getadminIdList(); 
		return adminIdList;
	}
	public Map<String,Object> getUserList(int currentPage) {
		//보여질 행의 갯수
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		//마지막 페이지 계산 
		//1. 보여질 테이블의 전체 행의 갯수
		double rowsCount = adminCommonMapper.getUserListCount();
		//int보다 더 넓은 자료형을 담아 줄 수 있는게 double 타입 int넣으면 소숫점 절삭
		// ex) 102/5 =20.4 int로 담을경우 소숫점 절삭되서 20으로 됨
		//2. 마지막 페이지
		int lastPage = (int) Math.ceil(rowsCount/rowPerPage);
		//Math.ceil 올림 처리
		// 처음 번호
        int startPageNum = 1;

        // 마지막 번호
        int endPageNum = (lastPage < 10)? lastPage : 10;

        /*
         * if(currentPage >= 7 && lastPage > 10) {
            if(currentPage < lastPage - 4) {
                startPageNum = currentPage - 5;
                endPageNum = currentPage + 4;
            }else {
                startPageNum = lastPage - 9;
                endPageNum = lastPage;
            }
        }*/
        
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
		
		//화면에 보여질 로그인이력 데이터
		List<Map<String,Object>> userList = adminCommonMapper.getUserList(paramMap);
		log.info("전체 회원 목록:{}",userList);

		//controller에 전달
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("userList", userList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
		
	}
	//회원 검증 여부
	public Map<String, Object> isValidUser(String userId, String userPw) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		boolean isValid = false;
		
		// 회원 검증 
		User user = adminCommonMapper.getUserInfoById(userId);
		if(user != null) {
			String checkPw = user.getUserPw();
			if(checkPw.equals(userPw)) {
				isValid = true;
				resultMap.put("userInfo", user);
			}
		}
		
		resultMap.put("isValid", isValid);
		
		return resultMap;
	}
	//회원 상세 조회
	public User getUserInfoById(String userId) {
		User userInfo = adminCommonMapper.getUserInfoById(userId);
		return userInfo;
	}
	
}
