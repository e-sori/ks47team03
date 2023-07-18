package ks47team03.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks47team03.admin.service.AdminKioskService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
	
	@SuppressWarnings("unchecked")
	@GetMapping("/installedKioskList")
	public String installedKioskList(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
			Model model) {
		Map<String,Object> resultMap = kioskService.getInstalledKioskListList(currentPage);
		int lastPage = (int)resultMap.get("lastPage");
		List<Map<String,Object>> installedKioskListList = (List<Map<String,Object>>)resultMap.get("installedKioskListList");
		log.info("installedKioskListList:{}",installedKioskListList);
		model.addAttribute("title","설치된 무인기기 목록");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		model.addAttribute("title","설치된 무인기기 목록");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("installedKioskListList", installedKioskListList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		
		return "admin/kiosk/installedKioskList";
	}
	
	
	

	


}
