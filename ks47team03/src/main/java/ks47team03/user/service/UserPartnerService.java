package ks47team03.user.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import ks47team03.user.dto.Kiosk;
import ks47team03.user.dto.Partner;
import ks47team03.user.mapper.UserCommonMapper;
import ks47team03.user.mapper.UserPartnerMapper;


@Service
public class UserPartnerService {
	// 로그 찍을 준비
		private static final Logger log = LoggerFactory.getLogger(UserPartnerService.class);
		//의존성 주입
		private final UserPartnerMapper partnerMapper;
		private final UserCommonMapper commonMapper;

		// 의존성 주입 끝 (생성자 메소드 방식)
		public UserPartnerService(UserPartnerMapper partnerMapper,UserCommonMapper commonMapper ) {
			this.partnerMapper = partnerMapper;
			this.commonMapper = commonMapper;

		}
		
		public void addBusinessCup(Partner partner) {
			partnerMapper.addBusinessCup(partner);
		}
		//아이디로 키오스크 리스트 조회
		public List<Kiosk>getInstalledKioskById(String loginId){
			List<Kiosk> installedKiost = partnerMapper.getInstalledKioskById(loginId);
			return installedKiost;
		}
		//레벨과 아이디 이용해 업체정보 조회
		public List<Kiosk> getPartnerInfoByLevel(String loginId){
			Map<String,Object> paramMap = new HashMap<String,Object>(); 
			String userLevel = commonMapper.getUserLevelById(loginId);
			log.info("userLeveldfsdfdsfsdfdsfdsf:{}",userLevel);
			paramMap.put("loginId", loginId);
			paramMap.put("userLevel", userLevel);
			List<Kiosk> partnerInfo =partnerMapper.getPartnerInfoByLevel(paramMap);
			return partnerInfo;
		}

}
