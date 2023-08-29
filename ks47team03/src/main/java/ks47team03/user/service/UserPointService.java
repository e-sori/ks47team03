package ks47team03.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ks47team03.user.dto.Account;
import ks47team03.user.dto.Point;
import ks47team03.user.mapper.UserPointMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@AllArgsConstructor
public class UserPointService {
	private final UserPointMapper userPointMapper;

	// 포인트 사용 또는 적립 신청 
	public void addPointUseSaveList(Point point) {
		userPointMapper.addPointUseSaveList(point);
		userPointMapper.modifyUserPoint(point);
	}
	
	// 특정 회원 계좌 수정
	public void modifyUserAccount(Account account) {
		userPointMapper.modifyUserAccount(account);		
	}	
	
	// 특정 회원 계좌 등록
	public void addUserAccount(Account account) {
		userPointMapper.addUserAccount(account);		
	}	
	
	// 특정 회원 계좌 조회
	public Account getUserAccount(String userId) {
		Account userAccount = userPointMapper.getUserAccount(userId);
		
		return userAccount; 
	}
	
	// 특정 회원 포인트 조회
	public Point getUserPoint(String userId) {
		Point userPoint = userPointMapper.getUserPoint(userId);
		
		return userPoint;
	};
	
	// 전체 회원 포인트 조회
	public Map<String,Object> getAllUserPoint(int currentPage){
		// 한 번에 최대로 보여줄 행의 개수
				int rowPerPage = 10;
				
				// 현재 페이지의 첫 번째 행의 인덱스
				int currentFirstIndex = (currentPage-1) * rowPerPage;		
				
				// 전체 행의 개수
				double rowsCount = userPointMapper.getAllUserPointCount("user_deposit_point");
				
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
				List<Map<String,Object>> allUserPointList = userPointMapper.getAllUserPoint(paramMap);
				log.info("AllUserPointList : {}", allUserPointList);
				
				// controller에 전달될 data
				paramMap.clear(); // map 객체 안의 data 초기화
				paramMap.put("allUserPointList", allUserPointList);
				paramMap.put("lastPageNumRefund", lastPageNum);
				paramMap.put("startPageNumRefund", startPageNum);
				paramMap.put("endPageNumRefund", endPageNum);

				
				
				return paramMap;
	};
}

