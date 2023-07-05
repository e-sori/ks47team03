package ks47team03.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.service.AdminService;

/**
 *  admin과 user 패키지에 같은 클래스가 존재할 때 오류 발생
 *  동일한 클래스 이름을 사용하고 싶으면 Bean에 저장될 이름을 지정해주면 된다.
 *  @Controller("Bean에 저장될 이름") / @Service("Bean에 저장될 이름")
 *  하지만 의존성을 주입할 때 @Qualifier라는 한정자(Bean의 이름을 지정해서 한정시킴) 사용해야 한다.
 *  @Qualifier("Bean에 저장된 이름")
 *  private final + @Qualifier 사용해서 의존성 주입해야함.
 */
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	// 의존성 주입
	private final AdminService adminService;
	
	/**
	 * @Qualifier("adminCommonService") CommonService commonService 는 무슨 뜻?
	 * 원래는 private final과 메서드 생성으로 의존성 주입
	 * 하지만 adminCommonService라는 Bean(객체)의 이름을 지정해서 저장해줬기 때문에
	 * 한정자를 사용해야 한다.
	 * 원래 의존성 주입할 때는 한정자 없이 데이터타입과 매개변수이름만 들어가지만
	 * 한정자를 데이터 타입 앞에 넣어주면 된다.
	 */
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/cupStock")
	public String cupStack(Model model) {
		model.addAttribute("title","구구 컵 재고 관리");
		return "admin/cupStock";
	}
	// admin 메인화면
	/**
	 * userCommonContorller와 경로 설정 똑같이 / 지만 
	 * localhost/ 를 치면 user 메인페이지가 뜨고
	 * lcalhost/admin을 치면 admin 메인페이지가 뜨는 이유 ?
	 * 위에서 @RequsestMapping을 admin으로 설정해줬기 때문
	 */
	@GetMapping("/")
	public String adminIndex(Model model) {
		model.addAttribute("title","관리자 메인화면");
		return "admin/main";
	}

}
