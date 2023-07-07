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


}
