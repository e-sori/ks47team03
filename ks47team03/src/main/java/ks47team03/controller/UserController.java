package ks47team03.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ks47team03.service.UserService;

@Controller
public class UserController {

	private final UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
		// 포인트 환급 화면
		@GetMapping("/point/myPointRefundSponsorship")
		public String pointRefundSponsorship(Model model) {
			
			model.addAttribute("title","구구컵 : 포인트 후원");
			
			return "user/point/myPointRefundSponsorship";
		}
	
	
		// 포인트 환급 화면
		@GetMapping("/point/myPointRefund")
		public String pointRefund(Model model) {
			
			model.addAttribute("title","구구컵 : 포인트 환급");
			
			return "user/point/myPointRefund";
		}
			
	
		// 나의 포인트 내역 화면
		@GetMapping("/point/myPoint")
		public String myPoint(Model model) {
			
			model.addAttribute("title","구구컵 : 나의포인트");
			
			return "user/point/myPoint";
		}
	
		// select 회원가입 선택화면
		@GetMapping("/selectJoin")
		public String selectJoin(Model model) {
			
			model.addAttribute("title","구구컵 : 회원가입 선택");
			
			return "user/selectJoin";
		}
		
		// userJoin 회원가입 화면
		@GetMapping("/userJoin")
		public String userJoin(Model model) {
			
			model.addAttribute("title","구구컵 : 사업자회원가입");
			
			return "user/userJoin";
		}
		
		// userJoin 회원가입 화면
		@GetMapping("/partnerJoin")
		public String partnerJoin(Model model) {
			
			model.addAttribute("title","구구컵 : 일반회원가입");
			
			return "user/partnerJoin";
		}

		// user 로그인 화면
		@GetMapping("/login")
		public String login(Model model) {
			
			model.addAttribute("title","구구컵 : 로그인");
			
			return "user/login";
		}
		
		// user 메인 화면
		@GetMapping("/")
		public String index(Model model) {
			
			model.addAttribute("title","구구컵프로젝트");
			
			return "user/main";
		}
}
