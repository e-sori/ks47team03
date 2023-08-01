package ks47team03.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDepositMapper {

	public List<Map<String,Object>> getUserDepositManageList(Map<String,Object>paramMap);
	public int getUserDepositManageListCount();
	
	public List<Map<String,Object>> getUserDepositPayList(Map<String,Object>paramMap);
	public int getUserDepositPayListCount();
	
	
}
