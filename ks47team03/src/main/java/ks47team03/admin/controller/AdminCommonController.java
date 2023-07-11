package ks47team03.admin.controller;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks47team03.admin.service.AdminCommonService;
import ks47team03.user.dto.User;
import lombok.extern.slf4j.Slf4j;



//@Controller("adminCommonController)
//	public AdminCommonController (@Qualifier("adminCommonService") AdminCommonService adminService) {
//		this.adminService = adminService;
//	}

/**
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
	
	/**
	 * @Qualifier("adminCommonService") CommonService commonService 는 무슨 뜻?
	 * 원래는 private final과 메서드 생성으로 의존성 주입
	 * 하지만 adminCommonService라는 Bean(객체)의 이름을 지정해서 저장해줬기 때문에
	 * 한정자를 사용해야 한다.
	 * 원래 의존성 주입할 때는 한정자 없이 데이터타입과 매개변수이름만 들어가지만
	 * 한정자를 데이터 타입 앞에 넣어주면 된다.
	 */
	public AdminCommonController(AdminCommonService adminService) {
		this.adminService = adminService;
	}
	//회원 관리 기준
	@GetMapping("/user/userMangeStandard")
	public String userMangeStandard(Model model) {
		model.addAttribute("title","회원 관리 기준");
		return "admin/user/userMangeStandard";
	}
	//전체 회원 관리
	@GetMapping("/user/userAll")
	public String userAll(Model model,
							@RequestParam(value="searchKey", required = false, defaultValue = "") String searchKey,
							@RequestParam(value="searchValue", required = false) String searchValue) {
		log.info("searchKey : {}", searchKey);
		log.info("searchValue : {}", searchValue);
		List<User> userList = adminService.getUserList(searchKey, searchValue);
		model.addAttribute("title", "회원목록");
		model.addAttribute("userList", userList);
		return "admin/user/userAll";
	}
	
	//회원 등급 관리
	@GetMapping("/user/gradeManage")
	public String gradeManage(Model model) {
		model.addAttribute("title","회원 등급 관리");
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
	//탈퇴 회원 관리
	@GetMapping("/user/loginHistory")
	public String loginHistory(Model model) {
		model.addAttribute("title","로그인 이력 조회");
		return "admin/user/loginHistory";
	}
	
	// admin 메인화면
	@GetMapping("/")
	public String index(Model model) {
		model.addAttribute("title","관리자 메인화면");
		return "admin/main";
	}

}
