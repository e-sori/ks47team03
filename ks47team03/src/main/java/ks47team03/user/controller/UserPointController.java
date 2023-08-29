package ks47team03.user.controller;

import jakarta.servlet.http.HttpSession;
import ks47team03.admin.mapper.AdminCommonMapper;
import ks47team03.user.dto.Account;
import ks47team03.user.dto.Point;
import ks47team03.user.service.UserPointService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/point")
@Slf4j
@AllArgsConstructor
public class UserPointController {
	
	// 의존성 주입
	private final UserPointService userPointService;
	private final AdminCommonMapper adminCommonMapper;
	
	// 포인트 후원 신청 처리 - 포인트 후원 처리
		@GetMapping("/addPointSponList")
		@ResponseBody
		public boolean addPointUseListSpon(int usePoint, HttpSession session) {
			String userId = (String) session.getAttribute("SID");
			Point addPointUseList = new Point();
			
			Point userPoint = userPointService.getUserPoint(userId);
			String pastNowHistoryNewCode = adminCommonMapper.getIncreaseCode("point_past_now_history");			
			String useHistoryNewCode = adminCommonMapper.getIncreaseCode("point_save_use_history");	
			int passHoldingpoint = userPoint.getCurrentHoldingPoint();
			int currentHoldingPoint = passHoldingpoint - usePoint;
					
			addPointUseList.setPointPastNowHistoryCode(pastNowHistoryNewCode);
			addPointUseList.setPointSaveUseHistoryCode(useHistoryNewCode);
			addPointUseList.setUserId(userId);
			addPointUseList.setPointSaveUseHistory(usePoint);
			addPointUseList.setPointSaveUseType("포인트 후원");
			addPointUseList.setSaveUseType("use");
			addPointUseList.setCurrentHoldingPoint(currentHoldingPoint);
			addPointUseList.setPassHoldingpoint(passHoldingpoint);
			
			userPointService.addPointUseSaveList(addPointUseList);

			return true;
		}	
	
	// 포인트 후원 신청 화면
	@GetMapping("/myPointSpon")
	public String pointRefundSponsorship(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("SID");
		int currentPoint = 0;
		
		Point userPoint = userPointService.getUserPoint(userId);
		
		if(userPoint !=null) currentPoint = userPoint.getCurrentHoldingPoint();
		
		model.addAttribute("title","구구컵 : 포인트 후원 신청");
		model.addAttribute("currentPoint",currentPoint);		
		
		return "user/point/myPointSponsorship";
	}
	
	// 포인트 환급 신청 처리3 - 포인트 환급 처리
	@GetMapping("/addPointRefundList")
	@ResponseBody
	public boolean addPointUseList(int usePoint, HttpSession session) {
		String userId = (String) session.getAttribute("SID");
		Point addPointUseList = new Point();
		
		Point userPoint = userPointService.getUserPoint(userId);
		String pastNowHistoryNewCode = adminCommonMapper.getIncreaseCode("point_past_now_history");			
		String useHistoryNewCode = adminCommonMapper.getIncreaseCode("point_save_use_history");	
		int passHoldingpoint = userPoint.getCurrentHoldingPoint();
		int currentHoldingPoint = passHoldingpoint - usePoint;
				
		addPointUseList.setPointPastNowHistoryCode(pastNowHistoryNewCode);
		addPointUseList.setPointSaveUseHistoryCode(useHistoryNewCode);
		addPointUseList.setUserId(userId);
		addPointUseList.setPointSaveUseHistory(usePoint);
		addPointUseList.setPointSaveUseType("포인트 환급");
		addPointUseList.setSaveUseType("use");
		addPointUseList.setCurrentHoldingPoint(currentHoldingPoint);
		addPointUseList.setPassHoldingpoint(passHoldingpoint);
		
		userPointService.addPointUseSaveList(addPointUseList);

		return true;
	}	
	
	// 포인트 환급 신청 처리2 - 계좌 수정
		@PostMapping("/modifyUserAccount")
		@ResponseBody
		public boolean modifyUserAccount(String bankName, String accountNum, HttpSession session) {
			String userId = (String) session.getAttribute("SID");
			Account addUserAccount = new Account();
			
			String accountName = (String) session.getAttribute("SNAME");	
			addUserAccount.setAccountNumber(accountNum);
			addUserAccount.setAccountUserName(accountName);
			addUserAccount.setBankName(bankName);
			addUserAccount.setUserId(userId);				
			
			userPointService.modifyUserAccount(addUserAccount);
			
			return true;
		}
	
	// 포인트 환급 신청 처리1 - 계좌 등록
	@PostMapping("/addUserAccount")
	@ResponseBody
	public boolean addUserAccount(String bankName, String accountNum, HttpSession session) {
		String userId = (String) session.getAttribute("SID");
		Account addUserAccount = new Account();
		
		String accountName = (String) session.getAttribute("SNAME");	
		addUserAccount.setAccountNumber(accountNum);
		addUserAccount.setAccountUserName(accountName);
		addUserAccount.setBankName(bankName);
		addUserAccount.setUserId(userId);				
		
		String userBankNewCode = adminCommonMapper.getIncreaseCode("user_bank");	
		addUserAccount.setUserBankCode(userBankNewCode);
		userPointService.addUserAccount(addUserAccount);	
		
		return true;
	}

	// 포인트 환급 신청 화면
	@GetMapping("/myPointRefund")
	public String pointRefund(Model model, HttpSession session) {
		// 예금주 value 입력 위해서 세션 네임 불러오기
		String accountName = (String) session.getAttribute("SNAME");
		// 사용자의 계좌 존재 여부 검사와 보유 포인트 불러오기 위해서 세션 아이디 불러오기
		String userId = (String) session.getAttribute("SID");
		// 은행, 계좌번호, 보유 포인트 초기화
		String bankName = "notSelect";
		String accountNum = "계좌번호를 입력해주세요.";
		int currentPoint = 0;
		
		Account userAccount = userPointService.getUserAccount(userId);
		Point userPoint = userPointService.getUserPoint(userId);
				
		if(userAccount != null) {			
			bankName = userAccount.getBankName();
			accountNum = userAccount.getAccountNumber();
		}
		
		if(userPoint !=null) currentPoint = userPoint.getCurrentHoldingPoint();
		
		model.addAttribute("title","구구컵 : 포인트 환급 신청");
		model.addAttribute("accountName",accountName);
		model.addAttribute("bankName",bankName);
		model.addAttribute("accountNum",accountNum);
		model.addAttribute("currentPoint",currentPoint);
		
		return "user/point/myPointRefund";
	}
		

	// 나의 포인트 내역 화면
	@GetMapping("/myPoint")
	public String myPoint(Model model) {
		
		model.addAttribute("title","구구컵 : 나의포인트");
		
		return "user/point/myPoint";
	}
	


}
