package ks47team03.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ks47team03.admin.service.AdminCupService;


@Controller
@RequestMapping("/admin/cup")
public class AdminCupController {
	
	// 의존성 주입
	private final AdminCupService cupService;

	
	public AdminCupController(AdminCupService cupService) {
		this.cupService = cupService;
	}
	//컵 상태 관리
	@SuppressWarnings("unchecked")
	@GetMapping("/cupStateManage")
	public String cupStateManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
									Model model) {
		//required= false 입력값 필수로 안받겠다. defaultValue = "1" 기본값 설정,문자열만 입력 가능 Modle=보내질 데이터
		Map<String,Object> resultMap = cupService.getCupStateList(currentPage);
		int lastPage = (int)resultMap.get("lastPage");
		
		List<Map<String,Object>> cupStateList = (List<Map<String,Object>>)resultMap.get("cupStateList");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		model.addAttribute("title", "컵 상태 관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("cupStateList", cupStateList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		return "admin/cup/cupStateManage";
	}
	
	// 컵 관련 기준 관리
	@GetMapping("/cupManageStandard")
	public String cupManageStandard(Model model) {
		model.addAttribute("title","구구컵 관련 기준 관리");
		return "admin/cup/cupManageStandard";
	}
	// 컵 재고 관리
	@GetMapping("/cupStockManage")
	public String cupStack(Model model) {
		model.addAttribute("title","구구 컵 재고 관리");
		return "admin/cup/cupStockManage";
	}
	// 컵 전체 이용내역 관리
	@GetMapping("/CupManage")
	public String CupManage(Model model) {
		model.addAttribute("title","컵 전체 이용내역 관리");
		return "admin/cup/CupManage";
	}

}
