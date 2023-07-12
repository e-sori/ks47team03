package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserBoardService;



@Controller
@RequestMapping("/board")
public class UserBoardController {
	
	// 의존성 주입
	private final UserBoardService boardService;
	
	
	public UserBoardController(UserBoardService boardService) {
		this.boardService = boardService;
	}

	// 커뮤니티 게시판
	@GetMapping("/communityBoardView")
	public String communityBoardView(Model model) {
		model.addAttribute("title","커뮤니티 게시판");
		return "user/board/communityBoardView";
	}


}
