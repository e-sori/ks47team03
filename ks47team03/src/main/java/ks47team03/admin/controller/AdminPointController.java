package ks47team03.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

	// 포인트 관련 기준 관리 화면
	@GetMapping("/pointStandardManage")
	@SuppressWarnings("unchecked")
	public String pointStandardManage(@RequestParam(value="currentPageMax", required = false, defaultValue = "1")int currentPageMax,
										@RequestParam(value="currentPageExpire", required = false, defaultValue = "1")int currentPageExpire,
										@RequestParam(value="currentPageMax", required = false, defaultValue = "1")int currentPageType,
										@RequestParam(value="currentPageMax", required = false, defaultValue = "1")int currentPageSave,
										@RequestParam(value="currentPageSave", required = false, defaultValue = "1")int currentPageRefund,
													Model model) {
		
		/* 5-2 포인트 환급 기준 조회 */
		Map<String,Object> pointRefundStandardResultMap = adminPointService.getPointRefundStandard(currentPageRefund);
		
		int startPageNumRefund = (int)pointRefundStandardResultMap.get("startPageNumRefund");
		int endPageNumRefund = (int)pointRefundStandardResultMap.get("endPageNumRefund");
		int lastPageNumRefund = (int)pointRefundStandardResultMap.get("lastPageNumRefund");		
		List<Map<String,Object>> pointRefundStandardList = (List<Map<String,Object>>)pointRefundStandardResultMap.get("pointRefundStandardList");
		
		model.addAttribute("startPageNumRefund", startPageNumRefund);
		model.addAttribute("endPageNumRefund", endPageNumRefund);
		model.addAttribute("lastPageNumRefund", lastPageNumRefund);
		model.addAttribute("currentPageRefund", currentPageRefund);
		model.addAttribute("pointRefundStandardList", pointRefundStandardList);
		
		/* 4-2 포인트 적립 기준 조회 */
		Map<String,Object> pointSaveStandardResultMap = adminPointService.getPointSaveStandard(currentPageSave);
		
		int startPageNumSave = (int)pointSaveStandardResultMap.get("startPageNumSave");
		int endPageNumSave = (int)pointSaveStandardResultMap.get("endPageNumSave");
		int lastPageNumSave = (int)pointSaveStandardResultMap.get("lastPageNumSave");		
		List<Map<String,Object>> pointSaveStandardList = (List<Map<String,Object>>)pointSaveStandardResultMap.get("pointSaveStandardList");
		
		model.addAttribute("startPageNumSave", startPageNumSave);
		model.addAttribute("endPageNumSave", endPageNumSave);
		model.addAttribute("lastPageNumSave", lastPageNumSave);
		model.addAttribute("currentPageSave", currentPageSave);
		model.addAttribute("pointSaveStandardList", pointSaveStandardList);
		
		/* 3-2 포인트 타입 기준 조회 */
		Map<String,Object> pointTypeStandardResultMap = adminPointService.getPointTypeStandard(currentPageType);
		
		int startPageNumType = (int)pointTypeStandardResultMap.get("startPageNumType");
		int endPageNumType = (int)pointTypeStandardResultMap.get("endPageNumType");
		int lastPageNumType = (int)pointTypeStandardResultMap.get("lastPageNumType");		
		List<Map<String,Object>> pointTypeStandardList = (List<Map<String,Object>>)pointTypeStandardResultMap.get("pointTypeStandardList");
		
		model.addAttribute("startPageNumType", startPageNumType);
		model.addAttribute("endPageNumType", endPageNumType);
		model.addAttribute("lastPageNumType", lastPageNumType);
		model.addAttribute("currentPageType", currentPageType);
		model.addAttribute("pointTypeStandardList", pointTypeStandardList);
		
		/* 2-2 포인트 만료 기간 기준 조회 */
		Map<String,Object> pointExpireStandardResultMap = adminPointService.getPointExpireStandard(currentPageExpire);
		
		int startPageNumExpire = (int)pointExpireStandardResultMap.get("startPageNumExpire");
		int endPageNumExpire = (int)pointExpireStandardResultMap.get("endPageNumExpire");
		int lastPageNumExpire = (int)pointExpireStandardResultMap.get("lastPageNumExpire");		
		List<Map<String,Object>> pointExpireStandardList = (List<Map<String,Object>>)pointExpireStandardResultMap.get("pointExpireStandardList");
		int pointExpire =  Integer.parseInt(String.valueOf(pointExpireStandardList.get(0).get("pointExpire")));
		
		model.addAttribute("startPageNumExpire", startPageNumExpire);
		model.addAttribute("endPageNumExpire", endPageNumExpire);
		model.addAttribute("lastPageNumExpire", lastPageNumExpire);
		model.addAttribute("currentPageExpire", currentPageExpire);
		model.addAttribute("pointExpireStandardList", pointExpireStandardList);
		model.addAttribute("pointExpire", pointExpire);

		
		/* 1-2 하루 최대 적립 포인트 횟수 기준 조회 */
		Map<String,Object> pointMaxCountStandardResultMap = adminPointService.getPointMaxCountStandard(currentPageMax);
		
		int startPageNumMax = (int)pointMaxCountStandardResultMap.get("startPageNumMax");
		int endPageNumMax = (int)pointMaxCountStandardResultMap.get("endPageNumMax");
		int lastPageNumMax = (int)pointMaxCountStandardResultMap.get("lastPageNumMax");		
		List<Map<String,Object>> pointMaxCountStandardList = (List<Map<String,Object>>)pointMaxCountStandardResultMap.get("pointMaxCountStandardList");
		int userMaximumCount = (int) pointMaxCountStandardList.get(0).get("userMaximumCount");
		
		model.addAttribute("title","포인트 관련 기준 관리");
		model.addAttribute("startPageNumMax", startPageNumMax);
		model.addAttribute("endPageNumMax", endPageNumMax);
		model.addAttribute("lastPageNumMax", lastPageNumMax);
		model.addAttribute("currentPageMax", currentPageMax);
		model.addAttribute("pointMaxCountStandardList", pointMaxCountStandardList);
		model.addAttribute("userMaximumCount", userMaximumCount);
		
		return "admin/point/pointStandardManage";
	}

}
