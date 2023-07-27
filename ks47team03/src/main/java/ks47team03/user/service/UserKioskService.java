package ks47team03.user.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ks47team03.user.dto.Kiosk;
import ks47team03.user.dto.Partner;
import ks47team03.user.mapper.UserKioskMapper;

@Service
public class UserKioskService {
	// 로그 찍을 준비
		private static final Logger log = LoggerFactory.getLogger(UserKioskService.class);
		//의존성 주입
		private final UserKioskMapper kioskMapper;

		// 의존성 주입 끝 (생성자 메소드 방식)
		public UserKioskService (UserKioskMapper kioskMapper) {
			this.kioskMapper = kioskMapper;
		}
		//지도에 뿌려줄 위도 경도 가져오기
		public List<Partner> getKioskLocationList(){
			List<Partner> kioskLocationList = kioskMapper.getKioskLocationList();
			return kioskLocationList;
		};
		//설치된 무인기기 리스트 가져오기
		public List<Kiosk> getinstalledKioskList(){
			List<Kiosk> installedKioskList = kioskMapper.getinstalledKioskList();
			return installedKioskList;
		};
}
