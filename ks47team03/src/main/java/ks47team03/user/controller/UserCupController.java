package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserCupService;


@Controller
@RequestMapping("/cup")
public class UserCupController {
	
	// 의존성 주입
	private final UserCupService cupService;
	
	
	public UserCupController(UserCupService cupService) {
		this.cupService = cupService;
	}

	


}
