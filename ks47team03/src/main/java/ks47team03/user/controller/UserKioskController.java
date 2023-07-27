package ks47team03.user.controller;



import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import ks47team03.user.dto.Kiosk;
import ks47team03.user.dto.Partner;
import ks47team03.user.service.UserKioskService;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
@RequestMapping("/kiosk")
public class UserKioskController {
	
	// 의존성 주입
	private final UserKioskService kioskService;
	
	
	public UserKioskController(UserKioskService kioskService) {
		this.kioskService = kioskService;
	}
	//무인기기 위치 조회
	@PostMapping("/kioskPlace")
	@ResponseBody
	public List<Partner> installedLocation() {
		
		List<Partner> kioskLocation = kioskService.getKioskLocationList();;
		
		return kioskLocation;
	}
	//무인기기 위치 조회
	@GetMapping("/kioskPlace")
	public String kioskPlace(Model model) {

		List<Partner> kioskLocation = kioskService.getKioskLocationList();
		List<Kiosk> installedKiosk = kioskService.getinstalledKioskList();
		model.addAttribute("title","무인기기 위치 조회");
		
		model.addAttribute("kioskLocation",kioskLocation);
		model.addAttribute("installedKiosk",installedKiosk);
		
		return "user/kiosk/kioskPlace";
	}
	
	
}	


