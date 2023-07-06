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

	


}
