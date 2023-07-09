package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserPartnerService;


@Controller
@RequestMapping("/partner")
public class UserPartnerController {
	
	// 의존성 주입
	private final UserPartnerService partnerService;
	
	
	public UserPartnerController(UserPartnerService partnerService) {
		this.partnerService = partnerService;
	}

	@GetMapping("/washDiscardCup")
	public String washDiscardCup(Model model) {
		model.addAttribute("title", "폐기컵 등록");
		return "user/partner/washDiscardCup";
	}
	@GetMapping("/businessAddCup")
	public String businessAddCup(Model model) {
		model.addAttribute("title", "추가 컵 배송");
		return "user/partner/businessAddCup";
	}
	@GetMapping("/businessKioskApply")
	public String businessKioskApply(Model model) {
		
		model.addAttribute("title","무인기기 신청");
		
		return "user/partner/businessKioskApply";
	}
	@GetMapping("/kioskInstalledList")
	public String kioskInstalledList(Model model) {
		
		model.addAttribute("title","설치된 무인기기 리스트");
		
		return "user/partner/kioskInstalledList";
	}
	@GetMapping("/businessKioskApplyResult")
	public String businessKioskApplyResult(Model model) {
		
		model.addAttribute("title","무인기기 신청 확인");
		
		return "user/partner/businessKioskApplyResult";
	}
	
	
	
	


}
