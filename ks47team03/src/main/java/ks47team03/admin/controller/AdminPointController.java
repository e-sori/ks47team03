package ks47team03.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks47team03.admin.service.AdminPointService;


@Controller
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

	// 포인트 관련 기준 관리 화면
	@GetMapping("/pointStandardManage")
	@SuppressWarnings("unchecked")
	public String pointStandardManage(@RequestParam(value="currentPage", required = false, defaultValue = "1")int currentPage,
													Model model) {
		
		/* 하루 최대 적립 포인트 횟수 기준 */
		Map<String,Object> resultMap = adminPointService.getPointMaxCountStandard(currentPage);
		
		int startPageNum = (int)resultMap.get("startPageNum");
		int endPageNum = (int)resultMap.get("endPageNum");
		int lastPageNum = (int)resultMap.get("lastPageNum");		
		List<Map<String,Object>> pointMaxCountStandardList = (List<Map<String,Object>>)resultMap.get("pointMaxCountStandardList");
		int userMaximumCount = (int) pointMaxCountStandardList.get(0).get("userMaximumCount");
		
		model.addAttribute("title","포인트 관련 기준 관리");
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("lastPageNum", lastPageNum);
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("pointMaxCountStandardList", pointMaxCountStandardList);
		model.addAttribute("userMaximumCount", userMaximumCount);
		
		return "admin/point/pointStandardManage";
	}

}
