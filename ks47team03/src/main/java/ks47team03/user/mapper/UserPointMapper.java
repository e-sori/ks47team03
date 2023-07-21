package ks47team03.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserPointMapper {
	
	public List<Map<String,Object>> getAllUserPoint(Map<String,Object> paraMap);
	
	public int getAllUserPointCount(String tableName);
}
