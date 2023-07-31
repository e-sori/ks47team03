package ks47team03.user.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import ks47team03.user.dto.Kiosk;
import ks47team03.user.dto.Partner;

@Mapper
public interface UserKioskMapper {
	//지도에 뿌릴 위도 경도 가져오기
	public List<Partner> getKioskLocationList();
	
	//매장에 설치된 키오스크 리스트 가져오기
	public List<Kiosk> getinstalledKioskListByCode(String partnerCode);
	//설치된 키오스크 리스트 가져오기
	public List<Kiosk> getinstalledKioskList();
	
}
