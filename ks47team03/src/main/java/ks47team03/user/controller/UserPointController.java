package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserPointService;


@Controller
@RequestMapping("/point")
public class UserPointController {
	
	// 의존성 주입
	private final UserPointService pointService;
	
	
	public UserPointController(UserPointService pointService) {
		this.pointService = pointService;
	}
	
	// 포인트 환급 화면
	@GetMapping("/myPointRefundSponsorship")
	public String pointRefundSponsorship(Model model) {
		
		model.addAttribute("title","구구컵 : 포인트 후원");
		
		return "user/point/myPointRefundSponsorship";
	}


	// 포인트 환급 화면
	@GetMapping("/myPointRefund")
	public String pointRefund(Model model) {
		
		model.addAttribute("title","구구컵 : 포인트 환급");
		
		return "user/point/myPointRefund";
	}
		

	// 나의 포인트 내역 화면
	@GetMapping("/myPoint")
	public String myPoint(Model model) {
		
		model.addAttribute("title","구구컵 : 나의포인트");
		
		return "user/point/myPoint";
	}
	


}
