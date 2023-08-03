package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import ks47team03.admin.mapper.AdminCommonMapper;
import ks47team03.user.dto.Account;
import ks47team03.user.dto.Point;
import ks47team03.user.dto.User;
import ks47team03.user.service.UserPointService;
import lombok.extern.slf4j.Slf4j;


@Controller
@RequestMapping("/point")
@Slf4j
public class UserPointController {
	
	// 의존성 주입
	private final UserPointService userPointService;
	private final AdminCommonMapper adminCommonMapper;
	
	
	public UserPointController(UserPointService userPointService, AdminCommonMapper adminCommonMapper) {
		this.userPointService = userPointService;	
		this.adminCommonMapper = adminCommonMapper;	
	}
	
	// 포인트 후원 화면
	@GetMapping("/myPointRefundSponsorship")
	public String pointRefundSponsorship(Model model) {
		
		model.addAttribute("title","구구컵 : 포인트 후원");
		
		return "user/point/myPointRefundSponsorship";
	}
	
	// 포인트 환급 신청 처리2 - 포인트 입력
	
	
	// 포인트 환급 신청 처리1 - 계좌 등록, 수정
	@PostMapping("/addUserAccount")
	@ResponseBody
	public boolean addUserAccount(String buttonType, String bankName, String accountNum, HttpSession session) {
		String userId = (String) session.getAttribute("SID");
		Account addUserAccount = new Account();
		
		String accountName = (String) session.getAttribute("SNAME");	
		addUserAccount.setAccountNumber(accountNum);
		addUserAccount.setAccountUserName(accountName);
		addUserAccount.setBankName(bankName);
		addUserAccount.setUserId(userId);				
		
		if(buttonType.equals("add")) {
			String userBankNewCode = adminCommonMapper.getIncreaseCode("user_bank");	
			addUserAccount.setUserBankCode(userBankNewCode);
			userPointService.addUserAccount(addUserAccount);
		}
		else if(buttonType.equals("modify")) userPointService.modifyUserAccount(addUserAccount);
		
		return true;
	}

	// 포인트 환급 신청 화면
	@GetMapping("/myPointRefund")
	public String pointRefund(Model model, HttpSession session) {
		
		String accountName = (String) session.getAttribute("SNAME");	
		String userId = (String) session.getAttribute("SID");
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
