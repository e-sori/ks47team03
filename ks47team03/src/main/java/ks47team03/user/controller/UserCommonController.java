package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import ks47team03.user.service.UserCommonService;


@Controller
public class UserCommonController {

	private final ks47team03.user.service.UserCommonService userService;
	
	public UserCommonController(UserCommonService userService) {
		this.userService = userService;
	}
	

		
		// join 회원가입 화면
		@GetMapping("/join")
		public String join(Model model) {
			
			model.addAttribute("title","구구컵 : 회원가입");
			
			return "user/join";
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
