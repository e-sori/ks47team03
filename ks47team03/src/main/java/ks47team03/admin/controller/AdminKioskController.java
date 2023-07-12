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
	@GetMapping("/installKioskManage")
	public String installKioskManage(Model model) {
		
		model.addAttribute("title","무인기기 위치 조회");
		
		return "admin/kiosk/installKioskManage";
	}
	@GetMapping("/returnlKioskManage")
	public String returnlKioskManage(Model model) {
		
		model.addAttribute("title","무인기기 위치 조회");
		
		return "admin/kiosk/returnlKioskManage";
	}
	@GetMapping("/installedKioskList")
	public String installedKioskList(Model model) {
		
		model.addAttribute("title","무인기기 위치 조회");
		
		return "admin/kiosk/installedKioskList";
	}
	
	
	

	


}
