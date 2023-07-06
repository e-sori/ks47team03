package ks47team03.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.admin.service.AdminKioskService;


@Controller
@RequestMapping("/admin/kiosk")

public class AdminKioskController {
	
	// 의존성 주입
	private final AdminKioskService kioskService;
	

	
	public AdminKioskController(AdminKioskService kioskService) {
		this.kioskService = kioskService;
	}

	


}
