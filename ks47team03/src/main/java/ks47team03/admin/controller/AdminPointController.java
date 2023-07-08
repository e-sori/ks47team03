package ks47team03.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.admin.service.AdminPointService;


@Controller
@RequestMapping("/admin/point")
public class AdminPointController {
	
	// 의존성 주입
	private final AdminPointService pointService;
	
	public AdminPointController(AdminPointService pointService) {
		this.pointService = pointService;
	}
	
	// 포인트 후원내역 관리
	@GetMapping("/pointSponsorshipManage")
	public String pointSponsorshipManage(Model model) {
		model.addAttribute("title","포인트 후원내역 관리");
		return "admin/point/pointSponsorshipManage";
	}		
		

	// 포인트 환급내역 관리
	@GetMapping("/pointRefundManage")
	public String pointRefundManage(Model model) {
		model.addAttribute("title","포인트 환급내역 관리");
		return "admin/point/pointRefundManage";
	}
	
	// 회원 포인트 관리
	@GetMapping("/pointManage")
	public String pointManage(Model model) {
		model.addAttribute("title","회원 포인트 관리");
		return "admin/point/pointManage";	}		
		

	// 포인트 관련 기준 관리
	@GetMapping("/standardManage")
	public String standardManage(Model model) {
		model.addAttribute("title","포인트 관련 기준 관리");
		return "admin/point/standardManage";
	}

}
