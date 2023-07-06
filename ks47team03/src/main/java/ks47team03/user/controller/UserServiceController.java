package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserServiceService;


@Controller
@RequestMapping("/service")
public class UserServiceController {
	
	// 의존성 주입
	private final UserServiceService serviceService;
	
	
	public UserServiceController(UserServiceService serviceService) {
		this.serviceService = serviceService;
	}

	


}
