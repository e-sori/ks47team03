package ks47team03.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import ks47team03.user.dto.User;
import ks47team03.user.dto.UserLevel;
import ks47team03.user.mapper.UserCommonMapper;

@Service
public class UserCommonService {
	
	
	private final UserCommonMapper userCommonMapper ;
	
	public UserCommonService(UserCommonMapper userCommonMapper) {
		this.userCommonMapper = userCommonMapper;
	}
	
		//회원 검증 여부
		public Map<String, Object> isValidUser(String userId, String userPw) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			
			boolean isValid = false;
			
			// 회원 검증 
			User user = userCommonMapper.getUserInfoById(userId);
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
			User userInfo = userCommonMapper.getUserInfoById(userId);
			return userInfo;
		}
		
		//회원 가입
		public void joinUser(User user) {
			userCommonMapper.joinUser(user);
		}
		
		//회원 db값
		@Service
		public class YourService {

		    // 데이터베이스 연동을 위해 필요한 리포지토리 또는 JDBC 템플릿 등을 여기에 주입합니다.
		    // 예를 들어 JPA를 사용한다면 EntityManager, JPA Repository 등을 주입합니다.
		    
		    // 해당 서비스에서 필요한 DB 저장 로직을 작성합니다.
		    public void saveValueToDB(String value) {
		        // DB에 value 값을 저장하는 로직을 구현합니다.
		    }
		}
		
		//회원 등급 조회
		public List<UserLevel> getUserLevelList(){
				
			List<UserLevel> userLevelList = userCommonMapper.getUserLevelList();
				
			return userLevelList;
		}
}
