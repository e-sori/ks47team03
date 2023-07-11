package ks47team03.admin.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ks47team03.user.dto.User;
import ks47team03.admin.mapper.AdminCommonMapper;

@Service
public class AdminCommonService {
	private static final Logger log = LoggerFactory.getLogger(AdminCommonService.class);
	
	private final AdminCommonMapper adminCommonMapper;
	
	public AdminCommonService(AdminCommonMapper adminCommonMapper) {
		this.adminCommonMapper = adminCommonMapper;
	}
	
	
		
	public List<User> getUserList(String searchKey, String searchValue){
		Map<String, Object> paramMap = null;
		
		if(searchValue != null) {
			switch (searchKey) {
				case "userId" -> {
					searchKey = "user_id";
				}
				case "userName" -> {
					searchKey = "user_name";
				}
				case "userEmail" -> {
					searchKey = "user_email";
				}
			
			}
			paramMap = new HashMap<String, Object>();
			paramMap.put("searchKey", searchKey);
			paramMap.put("searchValue", searchValue);
		}
		
		List<User> userList = adminCommonMapper.getUserList(paramMap);

		log.info("userList : {}", userList);

		return userList;
	
		
	}

}
