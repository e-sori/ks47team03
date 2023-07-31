package ks47team03.user.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

public interface UserPartnerMapper  {

import ks47team03.user.dto.Kiosk;
import ks47team03.user.dto.Partner;

@Mapper
public interface UserPartnerMapper {
	public List<Kiosk>getInstalledKioskById(String loginId);
	public void addBusinessCup(Partner partner);
	//레벨과 아이디로 제휴업체 정보 조회
	public List<Kiosk> getPartnerInfoByLevel(Map<String,Object> paramMap);
}
  
