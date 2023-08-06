package ks47team03.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks47team03.user.dto.Loghistory;
import ks47team03.user.dto.Static;
import ks47team03.user.dto.User;

@Mapper
public interface AdminCommonMapper {
	//관리자 조회
	public List<User>getadminIdList();
		
	//로그 기록 전체 리스트 조회
	public List<Map<String,Object>> getLogHistoryList(Map<String,Object>paramMap);
	//로그 기록 리스트 갯수
	public int getLogHistoryListCount();
	public int getLogHistoryListCount(Map<String,Object>paramMap);
	
	//한 회원 상태 조회
	public Loghistory getLogInfoById(String userId);
	//회원 등급 리스트 갯수
	public int getGradeManageListCount();
	public int getGradeManageListCount(Map<String,Object>paramMap);
	//회원 등급 리스트
	public List<Map<String,Object>> getGradeManageList(Map<String,Object>paramMap);
	
	//회원 조회
	public List<Map<String,Object>> getUserList(Map<String,Object>paramMap);
	//회원 리스트 갯수
	public int getUserListCount();
	public int getUserListCount(Map<String,Object>paramMap);
	
	//체크된 회원 삭제
	public void removeUser(List<String> userIdArr);
	//회원 수정
	public int modifyUser(User user);
	//한 회원 상태 조회
	public User getUserInfoByID(String userId);
	//회원 상태코드 리스트 조회
	public List<Static> getUserStaticList();
	
	//코드 번호 자동 증가	
	public String getIncreaseCode(String tableName);
}

