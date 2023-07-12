package ks47team03.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.admin.service.AdminServiceService;


@Controller
@RequestMapping("/admin/service")
public class AdminServiceController {
	
	// 의존성 주입
	private final AdminServiceService serviceService;
	
	
	public AdminServiceController(AdminServiceService serviceService) {
		this.serviceService = serviceService;
	}
	@GetMapping("/kioskAsApplyList")
	public String kioskAsApplyList(Model model) {
		
		model.addAttribute("title","AS 접수");
		
		return "admin/service/kioskAsApplyList";
	}
	


}
