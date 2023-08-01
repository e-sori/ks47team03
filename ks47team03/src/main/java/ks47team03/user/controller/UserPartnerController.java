package ks47team03.user.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpSession;
import ks47team03.admin.service.AdminCupService;
import ks47team03.user.dto.Cup;
import ks47team03.user.dto.FileDto;
import ks47team03.user.dto.Kiosk;
import ks47team03.user.dto.Partner;
import ks47team03.user.mapper.UserCommonMapper;
import ks47team03.user.mapper.UserKioskMapper;
import ks47team03.user.service.UserPartnerService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/partner")
public class UserPartnerController {
	
	@Value("${files.path}")
	private String filePath;
	// 의존성 주입
	private final UserPartnerService partnerService;
	private final AdminCupService adminCupService;
	private final UserCommonMapper commonMapper;
	private final UserKioskMapper kioskMapper;
	
	
	public UserPartnerController(UserPartnerService partnerService,AdminCupService adminCupService,UserCommonMapper commonMapper,UserKioskMapper kioskMapper) {
		this.partnerService = partnerService;
		this.adminCupService = adminCupService;
		this.commonMapper = commonMapper;
		this.kioskMapper =  kioskMapper;
	}
	

	//폐기컵 엑셀파일 업로드
	@PostMapping("/excel/fileupload")
	public String excelFileUpload(@RequestParam("excelFile") MultipartFile files,RedirectAttributes reAttr) {
		
		
		boolean isRead = adminCupService.addDiscardCupByExcelFile(files);
		log.info("읽기여부 : {}", isRead);
		if(isRead == false) {
			reAttr.addAttribute("msg", "중복된 자료가 있습니다!!확인 후 다시 업로드 부탁드립니다:)");	 
		}
		else{reAttr.addAttribute("msg", "업로드 완료❤");}
		return "redirect:/partner/washDiscardCup";
	}

	//폐기컵 리스트 조회
	@GetMapping("/washDiscardCup")
	public String washDiscardCup(Model model,
								@RequestParam(value="msg", required = false) String msg
								) {
		
		List<Cup> discardCupList = adminCupService.getDiscardCupList();
		List<FileDto> fileList = adminCupService.getFileList();
		if(msg != null) model.addAttribute("msg", msg);

		model.addAttribute("discardCupList", discardCupList);
		model.addAttribute("fileList",fileList );
		model.addAttribute("title", "폐기컵 등록");
		return "user/partner/washDiscardCup";
	}
	
	//추가컵 배송에서 키오스크 번호 확인
	@GetMapping("/addCupKioskNum")
	@ResponseBody
	public List<Kiosk> addCupKioskNum(String partnerCode) {
		List<Kiosk> kioskNumList = kioskMapper.getinstalledKioskListByCode(partnerCode);
		log.info("kioskNumList:{}",kioskNumList);
		return kioskNumList;
	}
	
	//추가컵 배송 신청
	@GetMapping("/businessAddCup")
	public String businessAddCup(Model model,
								 HttpSession session){
		String loginId = (String) session.getAttribute("SID");
		String userLevel = commonMapper.getUserLevelById(loginId);
		List<Partner> partnerCodeList = partnerService.getPartnerCodeById(loginId,userLevel);
		model.addAttribute("partnerCodeList", partnerCodeList);
		model.addAttribute("title", "추가 컵 배송");
		return "user/partner/businessAddCup";
	}
	@PostMapping("/applyAddCup")
	public String applyAddCup (Partner partner) {
		partnerService.addBusinessCup(partner);
		return "redirect:/partner/businessAddCup";
	}
	@GetMapping("/businessKioskApply")
	public String businessKioskApply(Model model) {
		
		model.addAttribute("title","무인기기 신청");
		
		return "user/partner/businessKioskApply";
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
