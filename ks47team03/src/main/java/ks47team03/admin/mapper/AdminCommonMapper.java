package ks47team03.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks47team03.user.dto.User;

@Mapper
public interface AdminCommonMapper {
	
	
	public List<User>getadminIdList();
	
	//전체 회원 조회
	public List<Map<String,Object>> getUserList (Map<String,Object>paramMap);
	//전체 회원 리스트 개수
	public int getUserListCount();
	
	//회원 상세 조회
	public User getUserInfoById(String userId);
}
