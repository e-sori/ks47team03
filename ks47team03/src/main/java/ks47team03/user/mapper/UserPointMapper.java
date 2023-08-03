package ks47team03.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks47team03.user.dto.Account;
import ks47team03.user.dto.Point;

@Mapper
public interface UserPointMapper {
	
	// 특정 회원 계좌 수정
	public void modifyUserAccount(Account account);
	
	// 특정 회원 계좌 등록
	public void addUserAccount(Account account);
	
	// 특정 회원 계좌 조회
	public Account getUserAccount(String userId);
	
	// 특정 회원 포인트 조회
	public Point getUserPoint(String userId);
	
	// 전체 회원 포인트 조회
	public List<Map<String,Object>> getAllUserPoint(Map<String,Object> paraMap);
	
	// 전체 회원 포인트 행 개수 조회
	public int getAllUserPointCount(String tableName);
}
