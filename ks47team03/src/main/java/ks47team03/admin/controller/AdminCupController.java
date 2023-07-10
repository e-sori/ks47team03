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
	// 컵 상태 관리
	@GetMapping("/cupStateManage")
	public String cupStateManage(Model model) {
		model.addAttribute("title","구구컵 상태 관리");
		return "admin/cup/cupStateManage";
	}
	// 컵 관련 기준 관리
	@GetMapping("/cupManageStandard")
	public String cupManageStandard(Model model) {
		model.addAttribute("title","구구컵 관련 기준 관리");
		return "admin/cup/cupManageStandard";
	}
	// 컵 재고 관리
	@GetMapping("/cupStockManage")
	public String cupStack(Model model) {
		model.addAttribute("title","구구 컵 재고 관리");
		return "admin/cup/cupStockManage";
	}
	// 컵 전체 이용내역 관리
	@GetMapping("/CupManage")
	public String CupManage(Model model) {
		model.addAttribute("title","컵 전체 이용내역 관리");
		return "admin/cup/CupManage";
	}

}
