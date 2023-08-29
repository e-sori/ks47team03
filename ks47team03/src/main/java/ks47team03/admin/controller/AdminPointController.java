package ks47team03.admin.controller;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ks47team03.admin.dto.AdminPoint;
import ks47team03.admin.service.AdminPointService;
import ks47team03.user.dto.User;
import ks47team03.user.service.UserCommonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/admin/point")
public class AdminPointController {
	
	// 의존성 주입
	private final AdminPointService adminPointService;
	private final UserCommonService userCommonService;
	
	// 포인트 후원내역 관리 화면
	@GetMapping("/pointSponsorshipManage")
	public String pointSponsorshipManage(Model model) {
		model.addAttribute("title","포인트 후원내역 관리");
		return "admin/point/pointSponsorshipManage";
	}		
		

	// 포인트 환급내역 관리 화면
	@GetMapping("/pointRefundManage")
	public String pointRefundManage(Model model) {
		model.addAttribute("title","포인트 환급내역 관리");
		return "admin/point/pointRefundManage";
	}
	
	// 회원 포인트 관리 화면
	@GetMapping("/pointManage")
	public String pointManage(Model model) {
		model.addAttribute("title","회원 포인트 관리");
		return "admin/point/pointManage";	
	}
	
	// 포인트 관련 기준 등록
	@PostMapping("/addPointStandard")
	public String addPointStandard(AdminPoint adminPoint, 
									RedirectAttributes reAttr) {
		
		adminPointService.addPointStandard(adminPoint);
			
		reAttr.addAttribute("message","등록 완료");
			
		return "redirect:/admin/point/pointStandardManage";
	}		
	
	// 포인트 관련 기준 등록 모달 (ajax로 데이터 리턴)
	@GetMapping("/getPointStandardModalList")
	@ResponseBody
	public Map<String,Object> getPointStandardModalList(@RequestParam(value="tableId")String tableId, 
									HttpSession session) {
		
		Map<String,Object> pointStandardModalReusltMap = new HashMap<String,Object>();
		
		String tableDbName = null;		
		
		if(tableId.equals("pills-max"))	tableDbName = "day_maximum_count";	
		else if(tableId.equals("pills-expire")) tableDbName = "point_expire_standard";			
		else if(tableId.equals("pills-save")) tableDbName = "point_save_standard";
		else if(tableId.equals("pills-refund")) tableDbName = "point_refund_standard";
		else tableDbName = "point_save_use_type";	
		
		String newPointStandardCode = adminPointService.getIncreaseCode(tableDbName);
		
		String SID = (String) session.getAttribute("SID");		
		User adminInfo = userCommonService.getUserInfoById(SID);		
		String adminPw = adminInfo.getUserPw();
		
		pointStandardModalReusltMap.put("newPointStandardCode", newPointStandardCode);
		pointStandardModalReusltMap.put("SID", SID);
		pointStandardModalReusltMap.put("adminPw", adminPw);
		
		return pointStandardModalReusltMap;
	}		
	
	// 포인트 관련 기준 관리 화면	(ajax로 데이터 리턴)
	@GetMapping("/getpointStandardList")
	@ResponseBody
	public Map<String,Object> getpointStandardList(@RequestParam(value="tableId")String tableId,
													Model model) {
		
		Map<String,Object> pointStandardResultMap = adminPointService.getPointStandardList(tableId);		
	
		model.addAttribute("title", "포인트 관련 기준 관리");		
		
		return pointStandardResultMap;
	}

	// 포인트 관련 기준 관리 화면	
	@GetMapping("/pointStandardManage")
	@SuppressWarnings("unchecked")
	public String pointStandardManage(@RequestParam(value="message", required = false) String message,
										Model model) {
		
		/* 포인트 기준 조회 */
		Map<String,Object> pointStandardResultMap = adminPointService.getPointStandardList("pills-max");
		
		List<Map<String,Object>>  pointStandardList = (List<Map<String,Object>> )pointStandardResultMap.get("pointStandardList");
		List<Map<String,Object>> codeUseList = (List<Map<String,Object>>) pointStandardResultMap.get("codeUseList");

		for (Map<String,Object> MaxCount : pointStandardList) { 
			if(MaxCount.get("codeUse").equals("사용중")) { 
				int useMaxCount = (int)MaxCount.get("useMaximumCount"); 
				model.addAttribute("useMaxCount", useMaxCount); 
				break; 
			} 
		}		  
			
		model.addAttribute("title","포인트 관련 기준 관리");
		model.addAttribute("codeUseList", codeUseList);
		model.addAttribute("pointStandardList", pointStandardList);	
		if(message != null) model.addAttribute("message", message);
		
		return "admin/point/pointStandardManage";
	}

}
