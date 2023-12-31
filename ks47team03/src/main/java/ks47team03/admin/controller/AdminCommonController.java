package ks47team03.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ks47team03.admin.mapper.AdminCommonMapper;
import ks47team03.admin.service.AdminCommonService;
import ks47team03.user.dto.Loghistory;
import ks47team03.user.dto.Static;
import ks47team03.user.dto.User;
import lombok.extern.slf4j.Slf4j;



//@Controller("adminCommonController)
//	public AdminCommonController (@Qualifier("adminCommonService") AdminCommonService adminService) {
//		this.adminService = adminService;
//	}
 /*
 *  admin과 user 패키지에 같은 클래스가 존재할 때 오류 발생
 *  동일한 클래스 이름을 사용하고 싶으면 Bean에 저장될 이름을 지정해주면 된다.
 *  @Controller("Bean에 저장될 이름") / @Service("Bean에 저장될 이름")
 *  하지만 의존성을 주입할 때 @Qualifier라는 한정자(Bean의 이름을 지정해서 한정시킴) 사용해야 한다.
 *  @Qualifier("Bean에 저장된 이름")
 *  private final + @Qualifier 사용해서 의존성 주입해야함.
 */
@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminCommonController {
	
	// 의존성 주입
	private final AdminCommonService adminService;
	private final AdminCommonMapper adminMapper;
	/**
	 * @Qualifier("adminCommonService") CommonService commonService 는 무슨 뜻?
	 * 원래는 private final과 메서드 생성으로 의존성 주입
	 * 하지만 adminCommonService라는 Bean(객체)의 이름을 지정해서 저장해줬기 때문에
	 * 한정자를 사용해야 한다.
	 * 원래 의존성 주입할 때는 한정자 없이 데이터타입과 매개변수이름만 들어가지만
	 * 한정자를 데이터 타입 앞에 넣어주면 된다.
	 */
	public AdminCommonController(AdminCommonService adminService, AdminCommonMapper adminMapper) {
		this.adminService = adminService;
		this.adminMapper = adminMapper;
	}
		
	//로그 기록
		@SuppressWarnings("unchecked")
		@GetMapping("/user/loginHistory")
		public String loginHistory(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
									 @RequestParam (value="searchKey", required= false) String searchKey,
								     @RequestParam (value="searchValue", required= false) String searchValue,
								     @RequestParam(value="msg", required = false) String msg,
									 Model model) {
			//search 
			
			//required= false 입력값 필수로 안받겠다. defaultValue = "1" 기본값 설정,문자열만 입력 가능 Modle=보내질 데이터
			Map<String,Object> resultMap = adminService.getLogHistoryList(currentPage,searchKey,searchValue);
			
			
			List<Map<String,Object>> logHistoryList = (List<Map<String,Object>>)resultMap.get("logHistoryList");
			log.info("logHistoryList:{}",logHistoryList);
			int startPageNum = (int) resultMap.get("startPageNum");
			int endPageNum = (int) resultMap.get("endPageNum");
			int lastPage = (int)resultMap.get("lastPage");
			int rowPerPage = (int) resultMap.get("rowPerPage");

			
			if(msg != null) model.addAttribute("msg", msg);
			model.addAttribute("title", "로그인 이력 조회");
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("logHistoryListCount",adminMapper.getLogHistoryListCount());
			model.addAttribute("logHistoryList", logHistoryList);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
			model.addAttribute("rowPerPage", rowPerPage);
			return "admin/user/loginHistory";
		}
	
	
	//회원 관리 기준
	@GetMapping("/user/userMangeStandard")
	public String userMangeStandard(Model model) {
		model.addAttribute("title","회원 관리 기준");
		return "admin/user/userMangeStandard";
	}
	//전체 회원 관리
	/*
	 * @GetMapping("Map<String,Object>paramMap") 
	 * public String userAll(Model model,
	 * 
	 * @RequestParam(value="searchKey", required = false, defaultValue = "") String
	 * searchKey,
	 * 
	 * @RequestParam(value="searchValue", required = false) String searchValue) {
	 * log.info("searchKey : {}", searchKey); log.info("searchValue : {}",
	 * searchValue); List<User> userList = adminService.getUserList(searchKey,
	 * searchValue); model.addAttribute("title", "회원목록");
	 * model.addAttribute("userList", userList); return "admin/user/userAll"; }
	 */
	//회원 수정 화면 
	@PostMapping("/user/userModify")
	public String userModify (User user) {
		log.info("userModify user:{}", user);
		adminService.modifyUser(user);
		
		return "redirect:/admin/user/userAll";
	}
	//회원 삭제
	@PostMapping("/user/userRemove")
	public String userRemove (Model model,
								  @RequestParam(name="userId") List<String> userIdArr,
								  RedirectAttributes reAttr) {
		adminService.removeUser(userIdArr);
		reAttr.addAttribute("msg", "삭제완료");
		log.info("userId:{}", "userId");
		
		return "redirect:/admin/user/userAll";
	}
	//회원 상태 수정 화면
	@GetMapping("/user/userModify")
	public String userModify(@RequestParam(value="userId") String userId,
			 					HttpSession session,
								Model model) {
		
		List<Static> userStaticInfo = adminService.getUserStaticList();
		User userInfo = adminService.getUserInfoByID(userId);
		List<User> adminInfo = adminService.getadminIdList();
		log.info("userInfo : {}" , userInfo);
		log.info("userStaticInfo : {}" , userStaticInfo);
		
		model.addAttribute("title", "회원 정보 수정");
		model.addAttribute("userStaticInfo", userStaticInfo);
		model.addAttribute("userInfo", userInfo);	
		model.addAttribute("adminInfo", adminInfo);	
		model.addAttribute("adminID", session.getAttribute("SID"));
		
		return "admin/user/userModify";
	}
	//전체 회원 관리
		@SuppressWarnings("unchecked")
		@GetMapping("/user/userAll")
		public String userAll(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
									 @RequestParam (value="searchKey", required= false) String searchKey,
								     @RequestParam (value="searchValue", required= false) String searchValue,
									 @RequestParam(value="msg", required = false) String msg,
									 Model model) {
			//search 
			
			//required= false 입력값 필수로 안받겠다. defaultValue = "1" 기본값 설정,문자열만 입력 가능 Modle=보내질 데이터
			Map<String,Object> resultMap = adminService.getUserList(currentPage,searchKey,searchValue);
			int lastPage = (int)resultMap.get("lastPage");
			
			List<Map<String,Object>> userList = (List<Map<String,Object>>)resultMap.get("userList");
			log.info("userlist:{}",userList);
			int startPageNum = (int) resultMap.get("startPageNum");
			int endPageNum = (int) resultMap.get("endPageNum");
			int rowPerPage = (int) resultMap.get("rowPerPage");
			
			if(msg != null) model.addAttribute("msg", msg);
			model.addAttribute("title", "전체 회원 관리");
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("userListCount",adminMapper.getUserListCount());
			model.addAttribute("userList", userList);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
			model.addAttribute("rowPerPage", rowPerPage);
			return "admin/user/userAll";
		}
	
	
	
	
	//회원 등급 관리
	@SuppressWarnings("unchecked")
	@GetMapping("/user/gradeManage")
	public String gradeManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
								
								@RequestParam(value="msg", required = false) String msg,
								Model model) {
		Map<String,Object> resultMap = adminService.getGradeManageList(currentPage);
		int lastPage = (int)resultMap.get("lastPage");
		
		List<Map<String,Object>> gradeManageList = (List<Map<String,Object>>)resultMap.get("gradeManageList");
		log.info("gradeManageList:{}",gradeManageList);
		
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		int rowPerPage = (int) resultMap.get("rowPerPage");
		
		if(msg != null) model.addAttribute("msg", msg);
		model.addAttribute("title","회원 등급 관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("gradeManageListCount",adminMapper.getGradeManageListCount());
		model.addAttribute("gradeManageList", gradeManageList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("rowPerPage", rowPerPage);
		return "admin/user/gradeManage";
	}
	
	//회원 바코드 관리
	@GetMapping("/user/barCodeManage")
	public String barCodeManage(Model model) {
		model.addAttribute("tilte","회원 바코드 관리");
		return "admin/user/barCodeManage";
	}
	// 신고 회원 관리
	@GetMapping("/user/blackUserManage")
	public String blackUserManage(Model model) {
		model.addAttribute("title","부정 회원 관리");
		return "admin/user/blackUserManage";
	}
	// 신고 회원 관리
	@GetMapping("/user/reportUserManage")
	public String reportUserManage(Model model) {
		model.addAttribute("title","신고 회원 관리");
		return "admin/user/reportUserManage";
	}
	//휴면 회원 관리
	@GetMapping("/user/userDormant")
	public String userDormant(Model model) {
		model.addAttribute("title","휴면 회원 관리");
		return "admin/user/userDormant";
	}
	//탈퇴 회원 관리
	@GetMapping("/user/userWithdrawal")
	public String userWithdrawal(Model model) {
		model.addAttribute("title","탈퇴 회원 관리");
		return "admin/user/userWithdrawal";
	}
	
	// admin 메인화면
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("title","관리자 메인화면");
		return "admin/main";
	}

}
