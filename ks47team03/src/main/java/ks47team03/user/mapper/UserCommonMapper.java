package ks47team03.user.mapper;

import org.apache.ibatis.annotations.Mapper;

import ks47team03.user.dto.User;

@Mapper
public interface UserCommonMapper {
	//회원 상세 조회
	public User getUserInfoById(String userId);
}
