package ks47team03.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import ks47team03.user.dto.Cup;
import ks47team03.user.dto.Static;

@Mapper
public interface AdminCupMapper {
	//한개 컵 상태 수정
	public int modifyCupState(Cup cup);
	//한개의 컵 상태 조회
	public Cup getCupInfoByQR(String cupQR);
	//컵 상태코드 리스트 조회
	public List<Static> getCupStaticList();
	//컵 상태 전체 리스트 조회
	public List<Map<String,Object>> getCupStateList (Map<String,Object>paramMap);
	//컵 상태 리스트 갯수
	public int getCupStateListCount();
}
