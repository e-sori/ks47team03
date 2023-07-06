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

	


}
