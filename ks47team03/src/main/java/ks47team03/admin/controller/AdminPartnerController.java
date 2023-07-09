package ks47team03.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.admin.service.AdminPartnerService;


@Controller
@RequestMapping("/admin/partner")
public class AdminPartnerController {
	
	// 의존성 주입
	private final AdminPartnerService partnerService;
	

	public AdminPartnerController(AdminPartnerService partnerService) {
		this.partnerService = partnerService;
	}

	//제휴 업체 승인 관리
	@GetMapping("/partnerApprovalManage")
	public String partnerApprovalManage(Model model) {
		model.addAttribute("title","구구컵 상태 관리");
		return "admin/partner/partnerApprovalManage";
	}
	//제휴 업체 회원 관리
	@GetMapping("/partnerMemberManage")
	public String partnerMemberManage(Model model) {
		model.addAttribute("title","구구컵 관련 기준 관리");
		return "admin/partner/partnerMemberManage";
	}


}
