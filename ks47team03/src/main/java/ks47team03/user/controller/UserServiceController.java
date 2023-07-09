package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserServiceService;




@Controller
@RequestMapping("/service")
public class UserServiceController {
	
	// 의존성 주입
	private final UserServiceService ServiceService;
	
	
	public UserServiceController(UserServiceService ServiceService) {
		this.ServiceService = ServiceService;
	}
	
	@GetMapping("/troubleKiosk")
	public String kioskPlace(Model model) {
		
		model.addAttribute("title","무인기기 고장 신고 접수");
		
		return "user/service/troubleKiosk";
	}
	
}	

