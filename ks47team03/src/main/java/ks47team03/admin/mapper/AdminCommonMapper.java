package ks47team03.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks47team03.user.dto.User;

@Mapper
public interface AdminCommonMapper {
	//전체 회원 조회
	public List<User> getUserList(Map<String, Object> paramMap);
}
