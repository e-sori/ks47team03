package ks47team03.admin.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AdminPointMapper {
	
	public List<Map<String,Object>> getPointMaxCountStandard(Map<String,Object> paramMap);

	public int getPointMaxCountStandardCount();
	
}
