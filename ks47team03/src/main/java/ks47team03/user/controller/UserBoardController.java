package ks47team03.user.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import ks47team03.user.dto.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import ks47team03.user.service.UserBoardService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.*;


@Controller
@RequestMapping("/board")
public class UserBoardController {
	//의존성 주입
	@Autowired
	private UserBoardService userBoardService;

	// 커뮤니티 게시판
	@GetMapping("/communityBoardView")
	public String communityBoardView(Model model, @RequestParam(defaultValue = "0") int page) {
		// List<Board> boards = userBoardService.communityBoardView();
		List<Board> boards = userBoardService.getBoards(); // 삭제되지 않은 게시글만 표기
		boards.sort(Comparator.comparingInt(board -> Integer.parseInt(board.getSimpleCode())));
		Collections.reverse(boards);

		int pageSize = 10;
		int start = page * pageSize;
		int end = Math.min(start + pageSize, boards.size());
		if (start > end) {
		}
		List<Board> pageOfBoards = boards.subList(start, end);

		int maxPage = (boards.size() - 1) / pageSize;  // Calculate the maximum page number

		// model.addAttribute("boardCount", communityBoardCount);
		model.addAttribute("boardCount", boards.size()); // 삭제되지 않은 게시글의 수를 세어 boardCount로 설정
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
	// 게시글 작성시 메시지창 띄우기
	@PostMapping("/communityBoardWritePro")
	public String communityBoardWritePro(Board board, HttpServletRequest request, Model model) {
		// 제목, 내용 비어 있는지 확인
		// 제목, 내용이 비어 있다면 메지지 반환 및 메서드 종료
		if (board.getCommunity_now_board_title() == null || board.getCommunity_now_board_title().isEmpty() ||
			board.getCommunity_now_board_content() == null || board.getCommunity_now_board_content().isEmpty()) {
			model.addAttribute("message", "제목과 내용은 필수 입력 사항입니다.");
			model.addAttribute("searchUrl", "/board/communityBoardWrite");
			return "user/board/message";
		}
		// admin_id 설정
		board.setAdmin_id("adminid001");
		userBoardService.communityBoardWrite(board, request);
		model.addAttribute("message","글 작성이 완료되었습니다.");
		model.addAttribute("searchUrl","/board/communityBoardView");
		return "user/board/message";
	}
	// 커뮤니티 게시글 상세 조회
	@GetMapping("/communityBoardDetail")
	public String communityBoardDetail(Model model, String boardCode, HttpServletRequest request){
		HttpSession session = request.getSession();
		// 세션에 게시글 읽은 정보 저장
		String sessionAttribute = "viewed-" + boardCode;
		Board board = userBoardService.communityBoardDetail(boardCode);
		if (board != null) {
			// 사용자가 게시글 읽지 않은 경우 증가 조회수 증가 코드
			if (session.getAttribute(sessionAttribute) == null) {
				board.setCommunity_now_board_view(board.getCommunity_now_board_view() + 1);
				userBoardService.saveBoard(board);
				// 사용자가 이미 이글을 읽었다는것을 세션에 기록
				session.setAttribute(sessionAttribute, true);
			}
			// 게시글 정보를 모델에 추가후 페이지를 로드
			model.addAttribute("detail", board);
		}
		return "user/board/communityBoardDetail";
	}
	// 커뮤니티 게시글 삭제
	@GetMapping("/communityBoardDelete")
	public ResponseEntity<String> communityBoardDelete(HttpServletRequest request, String boardCode){
		// 현재 로그인된 사용자 ID 가져오기
		HttpSession session = request.getSession();
		String currentUserId = (String) session.getAttribute("SID");
		try {
			// 게시글 삭제 요청을 서비스에 전달
			userBoardService.communityBoardDelete(boardCode, currentUserId);
			// 삭제가 성공하면, 성공 메시지를 담은 ResponseEntity를 반환
			return ResponseEntity.ok("게시글이 성공적으로 삭제되었습니다.");
		} catch (IllegalArgumentException e) {
			// 삭제를 실패했다면, 에러 메시지를 담은 ResponseEntity를 반환
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("작성자만 삭제할 수 있습니다.");
		} catch (RuntimeException e) {
			// 게시글을 찾을 수 없는 경우, 에러 메시지를 담은 ResponseEntity를 반환
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("게시글을 찾을 수 없습니다.");
		}
	}
	// 커뮤니티 게시글 수정
	@GetMapping("/communityBoardModify/{boardCode}")
	public String communityBoardModify(@PathVariable("boardCode") String boardCode,
									   Model model, RedirectAttributes redirectAttributes) {
		Board board = userBoardService.communityBoardDetail(boardCode);
		model.addAttribute("detail", board);
		return "user/board/communityBoardModify";
	}
	// 커뮤니티 게시글 (ID확인)
	@GetMapping("/checkAuthor/{boardCode}")
	public ResponseEntity<Map<String, Boolean>> checkAuthor(@PathVariable("boardCode") String boardCode, HttpServletRequest request) {
		Board board = userBoardService.communityBoardDetail(boardCode);
		HttpSession session = request.getSession();
		String currentUserId = (String) session.getAttribute("SID"); // 현재 로그인한 사용자의 아이디
		Map<String, Boolean> response = new HashMap<>();
		response.put("canEdit", board.getUser_id().equals(currentUserId));
		return ResponseEntity.ok(response);
	}
	@PostMapping("/communityBoardUpdate/{boardCode}")
	// 커뮤니티 게시글 수정 처리
	public String communityBoardUpdate(@PathVariable("boardCode") String boardCode, Board board){
		Board boardTemp = userBoardService.communityBoardDetail(boardCode);
		boardTemp.setCommunity_now_board_title(board.getCommunity_now_board_title());
		boardTemp.setCommunity_now_board_content(board.getCommunity_now_board_content());
		userBoardService.saveBoard(boardTemp);
		return "redirect:/board/communityBoardView";
	}
	// 커뮤니티 게시글 좋아요 처리
	@PostMapping("/likePost/{boardCode}")
	public ResponseEntity<Map<String, Object>> likePost(@PathVariable String boardCode, HttpSession session) {
		String currentUserId = (String) session.getAttribute("SID");
		boolean liked = userBoardService.toggleLike(currentUserId, boardCode);

		Map<String, Object> response = new HashMap<>();
		response.put("success", true);
		response.put("liked", liked);
		return ResponseEntity.ok(response);
	}

}
