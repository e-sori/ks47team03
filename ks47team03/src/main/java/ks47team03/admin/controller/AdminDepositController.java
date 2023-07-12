package ks47team03.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.admin.service.AdminDepositService;


@Controller
@RequestMapping("/admin/deposit")
public class AdminDepositController {
	
	// 의존성 주입
	private final AdminDepositService depositService;
	

	public AdminDepositController(AdminDepositService depositService) {
		this.depositService = depositService;
	}
	
	//보증금 내역 관리
	@GetMapping("/depositManage")
	public String depositManage(Model model) {
		model.addAttribute("title","보증금 내역 관리");
		return "admin/deposit/depositManage";
	}
	//보증금 결제 관리
	@GetMapping("/depositPayManage")
	public String depositPayManage(Model model) {
		model.addAttribute("title","보증금 결제 관리");
		return "admin/deposit/depositPayManage";
	}
	//보증금 환급 관리
	@GetMapping("/depositRefundManage")
	public String depositRefundManage(Model model) {
		model.addAttribute("title","보증금 환급 관리");
		return "admin/deposit/depositRefundManage";
	}
	// 보증금 기준 관리
		@GetMapping("/depositStandard")
		public String depositStandard(Model model) {
			model.addAttribute("title","보증금 기준 관리");
			return "admin/deposit/depositStandard";
		}
	


}
