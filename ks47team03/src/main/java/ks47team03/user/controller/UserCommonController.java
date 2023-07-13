package ks47team03.user.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ks47team03.user.dto.User;
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
		
		//logout
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			
			// 세션에 담겨져있는 data(정보) 초기화
			session.invalidate();
			
			return "redirect:/login";
		}
		
		@PostMapping("/login")
		public String login(@RequestParam(value="userId") String userId,
							@RequestParam(value="userPw") String userPw,
							HttpServletRequest request,
							HttpServletResponse response,
							HttpSession session,
							RedirectAttributes reAttr) {
			Map<String, Object> validMap = userService.isValidUser(userId, userPw);
			boolean isValid = (boolean) validMap.get("isValid");
			
			if(isValid) {
				User loginInfo = (User) validMap.get("userInfo");
				String userLevel = loginInfo.getUserLevel();
				String userName = loginInfo.getUserName();

				session.setAttribute("SID", userId);
				session.setAttribute("SLEVEL", userLevel);
				session.setAttribute("SNAME", userName);
				
				return "redirect:/";
			}
			
			reAttr.addAttribute("msg", "일치하는 회원의 정보가 없습니다.");
			
			return "redirect:/login";
		}
		// user 로그인 화면
		@GetMapping("/login")
		public String login(Model model, @RequestParam(value = "msg", required = false) String msg) {
			
			model.addAttribute("title", "구구컵 : 로그인");
			if(msg != null) model.addAttribute("msg", msg);
			
			return "user/login";
		}

		
		// user 메인 화면
		@GetMapping("/")
		public String index(Model model) {
			
			model.addAttribute("title","구구컵프로젝트");
			
			return "user/main";
		}
}
