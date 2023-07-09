package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserCupService;


@Controller
@RequestMapping("/mypage")
public class UserCupController {
	
	// 의존성 주입
	private final UserCupService cupService;
	
	
	public UserCupController(UserCupService cupService) {
		this.cupService = cupService;
	}

	// 나의 정보
	@GetMapping("/myInfo")
	public String myInfo(Model model) {			
		model.addAttribute("title","나의 정보");			
		return "user/mypage/myInfo";
	}
	
	// 나의 구구컵
	@GetMapping("/myGuguCup")
	public String myGuguCup(Model model) {
		model.addAttribute("title","나의 구구컵");
		return "user/mypage/myGuguCup";
	}
	
	// 제휴업체 신청
	@GetMapping("/partnerApply")
	public String partnerApply(Model model) {			
		model.addAttribute("title","제휴업체 신청");			
		return "user/mypage/partnerApply";
	}

}
