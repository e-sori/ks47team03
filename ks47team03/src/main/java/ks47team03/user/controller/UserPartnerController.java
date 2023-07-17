package ks47team03.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ks47team03.admin.service.AdminCupService;
import ks47team03.user.service.UserPartnerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/partner")
public class UserPartnerController {
	
	// 의존성 주입
	private final UserPartnerService partnerService;
	private final AdminCupService adminCupService;
	
	
	public UserPartnerController(UserPartnerService partnerService,AdminCupService adminCupService) {
		this.partnerService = partnerService;
		this.adminCupService =adminCupService;
	}

	@PostMapping("/excel/fileupload")
	public String excelFileUpload(@RequestParam("excelFile") MultipartFile files,RedirectAttributes reAttr) {
		
		reAttr.addAttribute("msg", "업로드 완료❤");
		log.info("읽기여부 : {}", files);
		boolean isRead = adminCupService.addDiscardCupByExcelFile(files);
		log.info("읽기여부 : {}", isRead);
		return "redirect:/partner/washDiscardCup";
	}
	
	@GetMapping("/washDiscardCup")
	public String washDiscardCup(Model model,
								@RequestParam(value="msg", required = false) String msg) {
		if(msg != null) model.addAttribute("msg", msg);
		model.addAttribute("title", "폐기컵 등록");
		return "user/partner/washDiscardCup";
	}
	
	@GetMapping("/businessAddCup")
	public String businessAddCup(Model model) {
		model.addAttribute("title", "추가 컵 배송");
		return "user/partner/businessAddCup";
	}
	@GetMapping("/businessKioskApply")
	public String businessKioskApply(Model model) {
		
		model.addAttribute("title","무인기기 신청");
		
		return "user/partner/businessKioskApply";
	}
	@GetMapping("/kioskInstalledList")
	public String kioskInstalledList(Model model) {
		
		model.addAttribute("title","설치된 무인기기 리스트");
		
		return "user/partner/kioskInstalledList";
	}
	@GetMapping("/businessKioskApplyResult")
	public String businessKioskApplyResult(Model model) {
		
		model.addAttribute("title","무인기기 신청 확인");
		
		return "user/partner/businessKioskApplyResult";
	}
	@GetMapping("/partnerInfo")
	public String partnerInfo(Model model) {
		
		model.addAttribute("title","업체 정보 조회");
		
		return "user/partner/partnerInfo";
	}
	@GetMapping("/businessMyKioskList")
	public String businessMyKioskList(Model model) {
		
		model.addAttribute("title","무인기기 확인");
		
		return "user/partner/businessMyKioskList";
	}
	
	
	
	


}
