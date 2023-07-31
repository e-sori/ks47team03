package ks47team03.admin.controller;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ks47team03.admin.service.AdminPointService;
import lombok.extern.slf4j.Slf4j;


@Controller
@Slf4j
@RequestMapping("/admin/point")
public class AdminPointController {
	
	// 의존성 주입
	private final AdminPointService adminPointService;
	
	public AdminPointController(AdminPointService adminPointService) {
		this.adminPointService = adminPointService;
	}
	
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
	
	// 하루 최대 적립 포인트 횟수 기준 등록 화면
	@GetMapping("/addPointMaxCountStandard")
	public String addPointStandard(Model model) {
		model.addAttribute("title","포인트 관련 기준 수정");
		return "admin/point/addPointMaxCountStandard";
	}	
	
	// 하루 최대 적립 포인트 횟수 기준 수정 화면
	@GetMapping("/modifyPointMaxCountStandard")
	public String mdifyPointMaxCountStandard(Model model) {
		model.addAttribute("title","포인트 관련 기준 수정");
		return "admin/point/modifyPointMaxCountStandard";
	}		
	
	// 포인트 관련 기준 관리 화면	(ajax로 데이터 받기)
	@GetMapping("/pointStandardManageClick")
	@ResponseBody
	public Map<String,Object> pointStandardMange(@RequestParam(value="tableId")String tableId,
													Model model) {
		
		Map<String,Object> pointStandardResultMap = adminPointService.getPointStandard(tableId);		
	
		model.addAttribute("title", "포인트 관련 기준 관리");		
		
		return pointStandardResultMap;
	}

	// 포인트 관련 기준 관리 화면	
	@GetMapping("/pointStandardManage")
	@SuppressWarnings("unchecked")
	public String pointStandardManage(Model model,HttpSession session) {
		
		/* 포인트 기준 조회 */
		Map<String,Object> pointStandardResultMap = adminPointService.getPointStandard("pills-max");
		
		List<Map<String,Object>>  pointStandardList = (List<Map<String,Object>> )pointStandardResultMap.get("pointStandardList");
		String sessionId = (String) session.getAttribute("SID");
		List<Map<String,Object>> codeUseList = (List<Map<String,Object>>) pointStandardResultMap.get("codeUseList");

		for (Map<String,Object> MaxCount : pointStandardList) { 
			if(MaxCount.get("codeUse").equals("사용가능")) { 
				int useMaxCount = (int)MaxCount.get("useMaximumCount"); 
				model.addAttribute("useMaxCount", useMaxCount); 
				break; 
			} 
		}		  
			
		model.addAttribute("title","포인트 관련 기준 관리");
		model.addAttribute("codeUseList", codeUseList);
		model.addAttribute("pointStandardList", pointStandardList);		
		model.addAttribute("SID", sessionId);		
		
		return "admin/point/pointStandardManage";
	}

}
