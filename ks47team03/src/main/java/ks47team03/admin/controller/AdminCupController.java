package ks47team03.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.admin.service.AdminCupService;


@Controller
@RequestMapping("/admin/cup")
public class AdminCupController {
	
	// 의존성 주입
	private final AdminCupService cupService;

	
	public AdminCupController(AdminCupService cupService) {
		this.cupService = cupService;
	}
	
	
	// 컵 재고 관리
	@GetMapping("/cupStock")
	public String cupStack(Model model) {
		model.addAttribute("title","구구 컵 재고 관리");
		return "admin/cup/cupStock";
	}
	


}
