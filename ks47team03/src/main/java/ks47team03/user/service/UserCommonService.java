package ks47team03.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;


import ks47team03.user.dto.User;
import ks47team03.user.mapper.UserCommonMapper;
import ks47team03.admin.mapper.*;

@Service
public class UserCommonService {
		

	private final AdminCommonMapper adminCommonMapper;
	private final UserCommonMapper userCommonMapper;
	
	// 생성자 메소드 의존성 주입방식
	public UserCommonService(UserCommonMapper userCommonMapper, AdminCommonMapper adminCommonMapper) {
		this.userCommonMapper = userCommonMapper;
		this.adminCommonMapper = adminCommonMapper;
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
