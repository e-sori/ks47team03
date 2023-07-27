package ks47team03.admin.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks47team03.admin.service.AdminDepositService;
import ks47team03.user.dto.DepositStandard;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/admin/deposit")
public class AdminDepositController {
	
	// 의존성 주입
	private final AdminDepositService depositService;

	public AdminDepositController(AdminDepositService depositService) {
		this.depositService = depositService;
	}	
	
	
	@GetMapping("/depositCheckSuccess")
	public String depositCheckSuccess(Model model) {
		model.addAttribute("title","결제 성공");
		return "admin/deposit/depositCheckSuccess";
	}
	@GetMapping("/depositCheckfail")
	public String depositCheckfail(Model model) {
		model.addAttribute("title","결제 실패");
		return "admin/deposit/depositCheckfail";
	}
	
	
	
	//보증금 내역 관리
	@SuppressWarnings("unchecked")
	@GetMapping("/depositManage")
	public String depositManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
			Model model) {
		Map<String,Object> resultMap = depositService.getDepositManageList(currentPage);
		int lastPage = (int)resultMap.get("lastPage");
		List<Map<String,Object>> depositManageList = (List<Map<String,Object>>)resultMap.get("depositManageList");
		log.info("depositManageList:{}",depositManageList);
		model.addAttribute("title","회원 보증금 관리");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		model.addAttribute("title","회원 보증금 관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("depositManageList", depositManageList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		
		return "admin/deposit/depositManage";
	}
	
	
	
	//보증금 결제 관리
	@SuppressWarnings("unchecked")
	@GetMapping("/depositPayManage")
	public String depositPayManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
			Model model) {
		Map<String,Object> resultMap = depositService.getDepositPayList(currentPage);
		int lastPage = (int)resultMap.get("lastPage");
		List<Map<String,Object>> depositPayList = (List<Map<String,Object>>)resultMap.get("depositPayList");
		log.info("depositPayList:{}",depositPayList);
		model.addAttribute("title","보증금 결제 관리");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
	
		model.addAttribute("title","보증금 결제 관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("depositPayList", depositPayList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		
		return "admin/deposit/depositPayManage";
	}
	
	//보증금 환급 관리
	@SuppressWarnings("unchecked")
	@GetMapping("/depositRefundManage")
	public String depositRefundManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
			Model model) {
		Map<String,Object> resultMap = depositService.getDepositRefundList(currentPage);
		int lastPage = (int)resultMap.get("lastPage");
		List<Map<String,Object>> depositRefundList = (List<Map<String,Object>>)resultMap.get("depositRefundList");
		log.info("depositRefundList:{}",depositRefundList);
		model.addAttribute("title","보증금 환급 관리");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
	
		model.addAttribute("title","보증금 환급 관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("depositRefundList", depositRefundList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
	
		return "admin/deposit/depositRefundManage";
	}
	
	
	// 보증금 기준 관리
		@SuppressWarnings("unchecked")
		@GetMapping("/depositStandard")
		public String depositStandard(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
				Model model) {
			Map<String,Object> resultMap = depositService.getDepositStandardList(currentPage);
			int lastPage = (int)resultMap.get("lastPage");
			
			List<Map<String,Object>> depositStandardList = (List<Map<String,Object>>)resultMap.get("depositStandardList");
			log.info("depositStandardList:{}",depositStandardList);
			
			int startPageNum = (int) resultMap.get("startPageNum");
			int endPageNum = (int) resultMap.get("endPageNum");
		
			model.addAttribute("title","보증금 기준 관리");
			model.addAttribute("currentPage", currentPage);
			model.addAttribute("lastPage", lastPage);
			model.addAttribute("depositStandardList", depositStandardList);
			model.addAttribute("startPageNum", startPageNum);
			model.addAttribute("endPageNum", endPageNum);
			
			return "admin/deposit/depositStandard";
		}
	
	
		@GetMapping("/modifyDepositStandard")
		public String modifyDepositStandardGet(@RequestParam(value="adminId") String adminId,
											Model model) {
			DepositStandard depositStandardInfo = depositService.getDepositStandardInfoById(adminId);
			
			model.addAttribute("title", "보증금 기준 수정");			
			model.addAttribute("depositStandardInfo", depositStandardInfo);
			
			return "admin/deposit/modifyDepositStandard";
		}		
		 @PostMapping("/modifyDepositStandard")
		 public String modifyDepositStandardPost(DepositStandard depositStandard) {		 		
			 depositService.modifyDepositStandard(depositStandard);

		 return "redirect:/deposit/depositStandard";
				 }
		
		 
		
		@PostMapping("/deleteDepositStandard")
		public String deleteDepositStandard(@RequestParam(value="waitingDepositStandardCode") String waitingDepositStandardCode,
											@RequestParam(value="adminId") String adminId,
											RedirectAttributes reAttr) {
				
				// 회원 여부 검증
				Map<String, Object> isValidMap = depositService.isValidMember(waitingDepositStandardCode, adminId);
				boolean isValid = (boolean) isValidMap.get("isValid");
				
				// 비밀번호 일치 회원탈퇴
				if(isValid) {
					DepositStandard deleteDepositStandardInfo = (DepositStandard) isValidMap.get("deleteDepositStandardInfo");

					// 회원탈퇴 서비스 메소드 호출(숙제: 2023년 06월 26일 확인)
					depositService.deleteDepositStandard(deleteDepositStandardInfo);
					return "redirect:/admin/deposit/depositStandard";
				}
				
				reAttr.addAttribute("adminId", adminId);
				reAttr.addAttribute("msg", "비번 확인해주세요");
				
				return "redirect:/admin/deposit/deleteDepositStandard";
			}
			
			@GetMapping("/deleteDepositStandard")
			public String deleteDepositStandard(@RequestParam(value="waitingDepositStandardCode") String waitingDepositStandardCode,
																@RequestParam(value="msg", required = false) String msg,
																Model model) {
				
				model.addAttribute("title", "회원탈퇴");
				model.addAttribute("waitingDepositStandardCode", waitingDepositStandardCode);
				if(msg != null) model.addAttribute("msg", msg);
				
				return "admin/deposit/deleteDepositStandard";
			}
		


}
