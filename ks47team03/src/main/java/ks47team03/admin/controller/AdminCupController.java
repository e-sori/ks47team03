package ks47team03.admin.controller;


import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import ks47team03.admin.service.AdminCommonService;
import ks47team03.admin.service.AdminCupService;
import ks47team03.user.dto.Cup;
import ks47team03.user.dto.Static;
import ks47team03.user.dto.User;


@Controller
@RequestMapping("/admin/cup")
public class AdminCupController {
	private static final Logger log = LoggerFactory.getLogger(AdminCupService.class);
	
	@Value("${files.path}")
	private String filePath;
	// 의존성 주입
	private final AdminCupService cupService;
	private final AdminCommonService commonService;

	public AdminCupController(AdminCupService cupService, AdminCommonService commonService) {
		this.cupService = cupService;
		this.commonService = commonService;
	}
	
	
	//폐기컵 관련 파일 업로드
	@PostMapping("/file/upload")
	public String archiveUpload(@RequestParam MultipartFile[] uploadfile, Model model, HttpServletRequest request,RedirectAttributes reAttr) {
		
		cupService.fileUpload(uploadfile);
		reAttr.addAttribute("msg", "파일 업로드 완료💗");
		return "redirect:/admin/cup/discardCupManage";
	}
	
	//폐기컵 관리 화면
	@GetMapping("/discardCupManage")
	@SuppressWarnings("unchecked")
	public String discardCupManage (@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
									@RequestParam(value="msg", required = false) String msg,
									Model model) {
		
		Map<String,Object> resultMap = cupService.getDiscardCupList(currentPage);
		int lastPage = (int)resultMap.get("lastPage");
		
		List<Map<String,Object>> discardCupList = (List<Map<String,Object>>)resultMap.get("discardCupList");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		
		model.addAttribute("fileList", cupService.getFileList());
		model.addAttribute("msg", msg);
		model.addAttribute("title", "컵 상태 관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("discardCupList", discardCupList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);

		return "admin/cup/discardCupManage";
	}
	
	//컵 수정 화면 
	@PostMapping("/cupStateModify")
	public String cupStateModify (Cup cup) {
		log.info("cupStateModify cup:{}", cup);
		cupService.modifyCupState(cup);
		
		return "redirect:/admin/cup/cupStateManage";
	}
	//컵 상태 삭제
	@PostMapping("/cupStateRemove")
	public String cupStateRemove (Model model,
								  @RequestParam(name="cupQR") List<String> cupQRArr,
								  RedirectAttributes reAttr) {
		cupService.removeCupState(cupQRArr);
		reAttr.addAttribute("msg", "삭제완료");
		
		/*
		 * //cupQRArr 배열을 돌아 값을 cupQR에 담아준다. for(String cupQR : cupQRArr) {
		 * 
		 * log.info("cupQR:{}",cupQR); }
		 */
		
		return "redirect:/admin/cup/cupStateManage";
	}
	//컵 상태 수정 화면
	@GetMapping("/cupStateModify")
	public String cupStateModify(@RequestParam(value="cupQR") String cupQR,
								Model model) {
		
		List<Static> cupStaticInfo = cupService.getCupStaticList();
		Cup cupInfo =cupService.getCupInfoByQR(cupQR);
		List<User> adminInfo = commonService.getadminIdList();
		log.info("cupInfo : {}" , cupInfo);
		log.info("cupStaticInfo : {}" , cupStaticInfo);
		
		model.addAttribute("title", "컵 상태 수정");
		model.addAttribute("cupStaticInfo", cupStaticInfo);
		model.addAttribute("cupInfo", cupInfo);	
		model.addAttribute("adminInfo", adminInfo);	
		
		
		return "admin/cup/cupStateModify";
	}
	//컵 상태 관리
	@SuppressWarnings("unchecked")
	@GetMapping("/cupStateManage")
	public String cupStateManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
								 @RequestParam (value="searchKey", required= false) String searchKey,
							     @RequestParam (value="searchValue", required= false) String searchValue,
								 @RequestParam(value="msg", required = false) String msg,
								 Model model) {
		//search 
		
		//required= false 입력값 필수로 안받겠다. defaultValue = "1" 기본값 설정,문자열만 입력 가능 Modle=보내질 데이터
		Map<String,Object> resultMap = cupService.getCupStateList(currentPage,searchKey,searchValue);
		int lastPage = (int)resultMap.get("lastPage");
		
		List<Map<String,Object>> cupStateList = (List<Map<String,Object>>)resultMap.get("cupStateList");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		if(msg != null) model.addAttribute("msg", msg);
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
	@SuppressWarnings("unchecked")
	@GetMapping("/cupStockManage")
	public String cupStockManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
						Model model) {
		
		
		Map<String,Object> resultMap = cupService.getCupStockList(currentPage);
		int lastPage = (int)resultMap.get("lastPage");
		
		List<Map<String,Object>> cupStockList = (List<Map<String,Object>>) resultMap.get("cupStockList");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		model.addAttribute("title","구구 컵 재고 관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("cupStockList", cupStockList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
	
		return "admin/cup/cupStockManage";
		}
	
	
	// 컵 전체 이용내역 관리
	@SuppressWarnings("unchecked")
	@GetMapping("/cupManage")
	public String cupManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
							Model model) {
		//required= false 입력값 필수로 안받겠다. defaultValue = "1" 기본값 설정,문자열만 입력 가능 Modle=보내질 데이터
		Map<String,Object> resultMap = cupService.getCupManageList(currentPage);
		int lastPage = (int)resultMap.get("lastPage");
		
		List<Map<String,Object>> cupManageList = (List<Map<String,Object>>)resultMap.get("cupManageList");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		model.addAttribute("title","컵 전체 이용내역 관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("cupManageList", cupManageList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		return "admin/cup/cupManage";
	}

}
