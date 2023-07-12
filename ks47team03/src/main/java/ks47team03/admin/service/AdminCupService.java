package ks47team03.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import ks47team03.admin.mapper.AdminCupMapper;
import ks47team03.user.dto.Cup;
import ks47team03.user.dto.Static;


@Service
@Transactional
public class AdminCupService {
	// 로그 찍을 준비
	private static final Logger log = LoggerFactory.getLogger(AdminCupService.class);
	//의존성 주입
	private AdminCupMapper adminCupMapper;
	
	// 의존성 주입 끝 (생성자 메소드 방식)
	public AdminCupService(AdminCupMapper adminCupMapper) {
		this.adminCupMapper = adminCupMapper;
	}
	//한개 컵 상태 상태 수정 
	public void modifyCupState(Cup cup) {
		adminCupMapper.modifyCupState(cup);
	}
	//한개 컵 현재 상태 조회
	public Cup getCupInfoByQR(String cupQR) {
		Cup cupInfo= adminCupMapper.getCupInfoByQR(cupQR);
		return cupInfo;
	}
	//컵 상태 코드 리스트 조회
	public List<Static> getCupStaticList(){
		List<Static> cupStaticList = adminCupMapper.getCupStaticList();
		return cupStaticList;
	};
	//컵 상태 조회
	public Map<String,Object> getCupStateList(int currentPage) {
		//보여질 행의 갯수
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		//마지막 페이지 계산 
		//1. 보여질 테이블의 전체 행의 갯수
		double rowsCount = adminCupMapper.getCupStateListCount();
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
		
		//화면에 보여질 로그인이력 데이터
		List<Map<String,Object>> cupStateList = adminCupMapper.getCupStateList(paramMap);
		log.info("컵 상태 리스트:{}",cupStateList);

		//controller에 전달
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("cupStateList", cupStateList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
		
	}
	//컵 전체 이용 내역 조회
	public Map<String,Object> getCupManageList(int currentPage) {
		//보여질 행의 갯수
		int rowPerPage = 16;
		
		//페이지 계산(시작될 행의 인덱스)
		int startIndex = (currentPage-1)*rowPerPage;
		
		//마지막 페이지 계산 
		//1. 보여질 테이블의 전체 행의 갯수
		double rowsCount = adminCupMapper.getCupManageListCount();
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
		
		//화면에 보여질 로그인이력 데이터
		List<Map<String,Object>> cupManageList = adminCupMapper.getCupManageList(paramMap);
		log.info("컵 상태 리스트:{}",cupManageList);

		//controller에 전달
		paramMap.clear(); // map 객체 안의 data초기화
		paramMap.put("lastPage", lastPage);
		paramMap.put("cupManageList", cupManageList);
		paramMap.put("startPageNum", startPageNum);
		paramMap.put("endPageNum", endPageNum);
		
		return paramMap;
		
	}

	
}
