package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserKioskService;


@RequestMapping("/kiosk")
public class UserKioskController {
	
	// 의존성 주입
	private final UserKioskService kioskService;
	
	public UserKioskController(UserKioskService kioskService) {
		this.kioskService = kioskService;
	}

	


}
