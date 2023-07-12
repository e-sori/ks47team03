package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserKioskService;



@Controller
@RequestMapping("/kiosk")
public class UserKioskController {
	
	// 의존성 주입
	private final UserKioskService kioskService;
	
	
	public UserKioskController(UserKioskService kioskService) {
		this.kioskService = kioskService;
	}
	
	@GetMapping("/kioskPlace")
	public String kioskPlace(Model model) {
		
		model.addAttribute("title","무인기기 위치 조회");
		
		return "user/kiosk/kioskPlace";
	}
	
	
}	


