package ks47team03.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminCupMapper {
	//컵 상태 전체 리스트 조회
	public List<Map<String,Object>> getCupStateList (Map<String,Object>paramMap);
	//컵 상태 리스트 갯수
	public int getCupStateListCount();
}
