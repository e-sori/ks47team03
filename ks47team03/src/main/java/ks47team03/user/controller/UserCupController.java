package ks47team03.user.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import ks47team03.admin.service.AdminCommonService;
import ks47team03.admin.service.AdminCupService;
import ks47team03.admin.service.AdminDepositService;
import ks47team03.admin.service.AdminPointService;
import ks47team03.user.dto.Deposit;
import ks47team03.user.dto.Point;
import ks47team03.user.dto.User;
import ks47team03.user.service.UserCupService;
import ks47team03.user.service.UserDepositService;
import ks47team03.user.service.UserPointService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/mypage")
public class UserCupController {
	
	// 의존성 주입
	private final UserCupService userCupService;
	private final UserPointService userPointService;
	private final UserDepositService userDepositService;
	private final AdminCupService adminCupService;
	private final AdminDepositService adminDepositService;
	private final AdminPointService adminPointService;
	private final AdminCommonService adminCommonService;

	//나의 바코드
	@GetMapping("/myBarcode")
	public String myBarcode(Model model) {
		model.addAttribute("title","나의 바코드");

		return "user/mypage/myBarcode";
	}

	// 나의 정보
	@GetMapping("/myInfo")
	public String myInfo(Model model, HttpSession session) {		
		String userId = (String) session.getAttribute("SID");
		Point userPoint = userPointService.getUserPoint(userId);
		Deposit userDeposit = userDepositService.getUserDeposit(userId);

		int currentPoint = 0;
		if(userPoint !=null) currentPoint = userPoint.getCurrentHoldingPoint();

		int currentDeposit = userDeposit.getCurrentHoldingDeposit();
		int cupRentalCount = userCupService.getUseCupCount(userId);

		model.addAttribute("title","나의 정보");
		model.addAttribute("currentPoint",currentPoint);
		model.addAttribute("currentDeposit", currentDeposit);
		model.addAttribute("cupRentalCount",cupRentalCount);

		return "user/mypage/myInfo";
	}
	
	// 나의 구구컵
	@GetMapping("/myCup")
	public String myCup(Model model, HttpSession session) {
		int cupRentalPeriod = adminCupService.getCupRentalPeriod();
		int depositPrice = adminDepositService.getDepositPrice();
		String userId = (String) session.getAttribute("SID");

		Map<String,Object> pointStandardResultMap = adminPointService.getPointStandardList("pills-max");
		List<Map<String,Object>>  pointStandardList = (List<Map<String,Object>> )pointStandardResultMap.get("pointStandardList");

		for (Map<String,Object> MaxCount : pointStandardList) {
			if(MaxCount.get("codeUse").equals("사용중")) {
				int useMaxCount = (int)MaxCount.get("useMaximumCount");
				model.addAttribute("useMaxCount", useMaxCount);
				break;
			}
		}

		int gradeMax = adminCommonService.getMaxGrade();

		User userInfo = adminCommonService.getUserInfoByID(userId);
		String userState = userInfo.getStaticCode();
		if(userState.equals("user_status_sc7_2")) userState = "일시정지";
		else if(userState.equals("user_status_sc7_3")) userState = "영구정지";
		else userState = null;

		int cupRentalCount = userCupService.getUseCupCount(userId);

		List<Map<String,Object>> userRentalCupList = userCupService.getRentalCupList(userId);
		log.info("sdfdsfsdfsfsdfsfds:{}",userRentalCupList);

		model.addAttribute("title","나의 구구컵");
		model.addAttribute("cupRentalPeriod",cupRentalPeriod);
		model.addAttribute("depositPrice",depositPrice);
		model.addAttribute("gradeMax",gradeMax);
		model.addAttribute("userState",userState);
		model.addAttribute("cupRentalCount",cupRentalCount);
		model.addAttribute("userRentalCupList",userRentalCupList);

		return "user/mypage/myCup";
	}
	
	// 제휴업체 신청
	@GetMapping("/partnerApply")
	public String partnerApply(Model model) {			
		model.addAttribute("title","제휴업체 신청");			
		return "user/mypage/partnerApply";
	}

}
