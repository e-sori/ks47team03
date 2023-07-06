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

	


}
