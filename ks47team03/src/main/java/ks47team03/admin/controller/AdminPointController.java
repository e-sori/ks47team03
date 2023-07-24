package ks47team03.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	@PostMapping("/pointStandardManage")
	@SuppressWarnings("unchecked")
	public String pointStandardMange(@RequestParam(value="currentPage", required = false, defaultValue = "1")int currentPage,
										@RequestParam(value="tableId", required = false, defaultValue = "1")String tableId,
										Model model,
										HttpSession session) {
		
		Map<String,Object> pointStandardResultMap = adminPointService.getPointStandard(currentPage,tableId);
		
		
		int startPageNum = (int)pointStandardResultMap.get("startPageNum");
		int endPageNum = (int)pointStandardResultMap.get("endPageNum");
		int lastPageNum = (int)pointStandardResultMap.get("lastPageNum");		
		List<Map<String,Object>> pointStandardList = (List<Map<String,Object>>)pointStandardResultMap.get("pointStandardList");
		String sessionId = (String) session.getAttribute("SID");
		
		
		
		model.addAttribute("title","포인트 관련 기준 관리");
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pointStandardList", pointStandardList);	
		model.addAttribute("SID", sessionId);
		
		if(tableId.equals("pills-max")) {
			int useMaximumCount = (int) pointStandardList.get(0).get("useMaximumCount");
			model.addAttribute("useMaximumCount", useMaximumCount);
		}else if(tableId.equals("pills-use")) {
			List<Map<String,Object>> codeUseList = (List<Map<String,Object>>) pointStandardResultMap.get("codeUseList");
			model.addAttribute("codeUseList", codeUseList);
		} // 나중에 모든 테이블 사용 유무 추가해주고 else if 없애기
		
		return "admin/point/pointStandardManage";
	}

	// 포인트 관련 기준 관리 화면	
	@GetMapping("/pointStandardManage")
	@SuppressWarnings("unchecked")
	public String pointStandardManage(@RequestParam(value="currentPage", required = false, defaultValue = "1")int currentPage,
										@RequestParam(value="tableId", required = false, defaultValue = "pills-max")String tableId,
													Model model,
													HttpSession session) {
		
		/* 포인트 기준 조회 */
		Map<String,Object> pointStandardResultMap = adminPointService.getPointStandard(currentPage,tableId);
		
		int startPageNum = (int)pointStandardResultMap.get("startPageNum");
		int endPageNum = (int)pointStandardResultMap.get("endPageNum");
		int lastPageNum = (int)pointStandardResultMap.get("lastPageNum");		
		List<Map<String,Object>> pointStandardList = (List<Map<String,Object>>)pointStandardResultMap.get("pointStandardList");
		int useMaximumCount = (int) pointStandardList.get(0).get("useMaximumCount");
		String sessionId = (String) session.getAttribute("SID");
		List<Map<String,Object>> codeUseList = (List<Map<String,Object>>) pointStandardResultMap.get("codeUseList");		
		
		model.addAttribute("title","포인트 관련 기준 관리");
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pointStandardList", pointStandardList);
		model.addAttribute("useMaximumCount", useMaximumCount);
		model.addAttribute("codeUseList", codeUseList);
		model.addAttribute("SID", sessionId);
		
		return "admin/point/pointStandardManage";
	}

}
