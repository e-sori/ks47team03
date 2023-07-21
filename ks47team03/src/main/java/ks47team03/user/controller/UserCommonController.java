package ks47team03.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ks47team03.user.dto.User;
import ks47team03.user.mapper.UserCommonMapper;
import ks47team03.user.service.UserCommonService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class UserCommonController {

	private final ks47team03.user.service.UserCommonService userCommonService;
	private final ks47team03.user.mapper.UserCommonMapper userCommonMapper;
	
	public UserCommonController(UserCommonService userCommonService, UserCommonMapper userCommonMapper) {
		this.userCommonService = userCommonService;
		this.userCommonMapper  = userCommonMapper;
	}
		
	
		//logout
		@GetMapping("/logout")
		public String logout(HttpSession session) {
			
			// 세션에 담겨져있는 data(정보) 초기화
			session.invalidate();
			
			return "redirect:/login";
		}
		//login
		@PostMapping("/login")
		public String login(@RequestParam(value="userId") String userId,
							@RequestParam(value="userPw") String userPw,
							HttpServletRequest request,
							HttpServletResponse response,
							HttpSession session,
							RedirectAttributes reAttr) {
			Map<String, Object> validMap = userCommonService.isValidUser(userId, userPw);
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
			
			model.addAttribute("title","구구컵 : 로그인");
			if(msg != null) model.addAttribute("msg", msg);
			
			return "user/login";
		}
		
		@PostMapping("/join")
		public String joinUser(User user) {
			
			log.info("회원가입시 입력정보: {}", user);
			
			userCommonService.joinUser(user);
			
			// response.sendRedirect("/member/memberList");
			// spring framework mvc 에서는 controller의 리턴값에 redirect: 키워드로 작성
			// redirect: 키워드를 작성할 경우 그다음의 문자열은 html파일 논리 경로가 아닌 주소를 의미
			return "redirect:/";
		}
		
		@PostMapping("/idCheck")
		@ResponseBody
		public boolean idCheck(@RequestParam(value="userId") String userId) {
			log.info("id 중복체크:{}", userId);
			boolean result = userCommonMapper.idCheck(userId);
			log.info("id 중복체크 결과값:{}", result);
			return result;
		} 
		
		// join 회원가입 화면
		@GetMapping("/join")
		public String joinUser(Model model) {
			
			model.addAttribute("title","구구컵 : 회원가입");
			
			return "user/join";
		}
		
		// 프로젝트 소개 화면
		@GetMapping("/projectIntro")
        public String projectIntro(Model model) {

            model.addAttribute("title","구구컵프로젝트를 소개합니다");

            return "user/projectIntro";
        }
		
		
		// user 메인 화면
		@GetMapping("/")
		public String main(Model model) {
			
			model.addAttribute("title","구구컵프로젝트");
			
			return "user/main";
		}
		
}
