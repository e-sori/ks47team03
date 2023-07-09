package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserDepositService;


@Controller
@RequestMapping("/deposit")
public class UserDepositController {
	
	// 의존성 주입
	private final UserDepositService depositService;
	
	
	public UserDepositController(UserDepositService depositService) {
		this.depositService = depositService;
	}


	@GetMapping("/mydeposit")
	public String mydeposit(Model model) {
		
		model.addAttribute("title","보증금 조회");
		
		return "user/deposit/mydepositPay.html";
	}
	@GetMapping("/mydepositPay")
	public String mydepositPay(Model model) {
		
		model.addAttribute("title","보증금 결제 신청");
		
		return "user/deposit/mydepositPay";
	}
	@GetMapping("/mydepositRefund")
	public String pointRefundSponsorship(Model model) {
		
		model.addAttribute("title","보증금 환급");
		
		return "user/deposit/mydepositRefund";
	}
	
	


}
