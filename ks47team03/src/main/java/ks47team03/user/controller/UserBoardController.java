package ks47team03.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import ks47team03.user.dto.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ks47team03.user.service.UserBoardService;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;


@Controller
@RequestMapping("/board")
public class UserBoardController {
	//의존성 주입
	@Autowired
	private UserBoardService userBoardService;

	// 의존성 주입
	private final UserBoardService boardService;

	public UserBoardController(UserBoardService boardService) {
		this.boardService = boardService;
	}

	// 커뮤니티 게시판
	@GetMapping("/communityBoardView")
	public String communityBoardView(Model model, @RequestParam(defaultValue = "0") int page) {
		List<Board> boards = userBoardService.communityBoardView();
		boards.sort(Comparator.comparingInt(board -> Integer.parseInt(board.getSimpleCode())));
		Collections.reverse(boards);

		int pageSize = 10;
		int start = page * pageSize;
		int end = Math.min(start + pageSize, boards.size());
		if (start > end) {
		}
		List<Board> pageOfBoards = boards.subList(start, end);

		int maxPage = (boards.size() - 1) / pageSize;  // Calculate the maximum page number

		model.addAttribute("list", pageOfBoards);
		model.addAttribute("currentPage", page);  // Current page number
		model.addAttribute("maxPage", maxPage);  // Maximum page number

		return "user/board/communityBoardView";
	}
	// 커뮤니티 게시글 작성
	@GetMapping("/communityBoardWrite")
	public String communityBoardWrite(){
		return "user/board/communityBoardWrite";
	}
	// 커뮤니티 게시글 작성폼
	// 게시글 작성시 로그인되어있는 작성자 입력
	@PostMapping("/communityBoardWritePro")
	public String communityBoardWritePro(Board board, HttpServletRequest request) {
		userBoardService.communityBoardWrite(board, request);
		return "";
	}
	// 커뮤니티 게시글 상세 조회
	@GetMapping("/communityBoardDetail")
	public String communityBoardDetail(Model model, String boardCode){
		model.addAttribute("detail",userBoardService.communityBoardDetail(boardCode));
		return "user/board/communityBoardDetail";
	}
	// 커뮤니티 게시글 삭제
	@GetMapping("/communityBoardDelete")
	public String communityBoardDelete(String boardCode){
		userBoardService.communityBoardDelete(boardCode);
		return "redirect:/board/communityBoardView";
	}

}
