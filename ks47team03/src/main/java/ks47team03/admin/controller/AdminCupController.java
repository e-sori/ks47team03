package ks47team03.admin.controller;


import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ks47team03.admin.mapper.AdminCupMapper;
import ks47team03.admin.mapper.AdminKioskMapper;
import ks47team03.admin.service.AdminCommonService;
import ks47team03.admin.service.AdminCupService;
import ks47team03.user.dto.Cup;
import ks47team03.user.dto.FileDto;
import ks47team03.user.dto.Kiosk;
import ks47team03.user.dto.Partner;
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
	private final AdminCupMapper cupMapper;
	private final AdminKioskMapper kioskMapper;
	private final AdminCommonService commonService;

	public AdminCupController(AdminCupService cupService, AdminCommonService commonService,AdminCupMapper cupMapper,AdminKioskMapper kioskMapper) {
		this.cupService = cupService;
		this.commonService = commonService;
		this.cupMapper= cupMapper;
		this.kioskMapper = kioskMapper;
	}
	//추가컵 배송 
	@GetMapping("/addCupApply")
	public String  addCupApply(Model model) {
		List<Partner>addCupApplyList=cupMapper.getAddCupApplyList();
		
		model.addAttribute("addCupApplyList", addCupApplyList);
		return "admin/cup/addCupApply";
	}
	//폐기컵 관련 파일 업로드
	@PostMapping("/file/upload")
	public String archiveUpload(@RequestParam MultipartFile[] uploadfile, Model model, HttpServletRequest request,RedirectAttributes reAttr) {
		
		cupService.fileUpload(uploadfile);
		reAttr.addAttribute("msg", "파일 업로드 완료💗");
		return "redirect:/admin/cup/discardCupManage";
	}
	//폐기컵 삭제
	@PostMapping("/discardCupRemove")
	public String discardCupRemove (Model model,
								  @RequestParam(name="cupQR") List<String> cupQRArr
								 ) {
		cupService.removeDiscardCup(cupQRArr);
		
		
		/*
		 * //cupQRArr 배열을 돌아 값을 cupQR에 담아준다. for(String cupQR : cupQRArr) {
		 * 
		 * log.info("cupQR:{}",cupQR); }
		 */
		
		return "redirect:/admin/cup/discardCupManage";
	}
	//폐기컵 관리 화면
	@GetMapping("/discardCupManage")
	@SuppressWarnings("unchecked")
	public String discardCupManage (
									HttpServletRequest request,
									Model model) {
		
		List<Cup> discardCupList = cupService.getDiscardCupList();		
		model.addAttribute("fileList", cupService.getFileList());
		model.addAttribute("title", "컵 상태 관리");
		model.addAttribute("discardCupList", discardCupList);
		return "admin/cup/discardCupManage";
	}
	
	//파일 다운로드
	@RequestMapping(value="/file/download")
	@ResponseBody
	public ResponseEntity<Object> archiveDownload(@RequestParam(value="fileIdx", required = false) String fileIdx
												   ,HttpServletRequest request
												   ,HttpServletResponse response) throws URISyntaxException{

		if(fileIdx != null) {
			FileDto fileDto = cupService.getFileInfoByIdx(fileIdx);
			
			File file = new File(filePath + fileDto.getFilePath());
			Path path = Paths.get(file.getAbsolutePath());
	        Resource resource;
			try {
				resource = new UrlResource(path.toUri());
				String contentType = null;
				contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
				if(contentType == null) {
					contentType = "application/octet-stream";
				}
				return ResponseEntity.ok()
						.contentType(MediaType.parseMediaType(contentType))
						.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + URLEncoder.encode(fileDto.getFileOriginalName(),"UTF-8") + "\";")
						.body(resource);
			} catch (MalformedURLException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		URI redirectUri = new URI("/");
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.setLocation(redirectUri);
		
        return new ResponseEntity<>(httpHeaders, HttpStatus.SEE_OTHER);
	}
	
	//다운로드 파일 삭제
	@GetMapping("/file/deleteFile")
	public String fileDelete(@RequestParam(value="fileIdx") String fileIdx) {

		if(fileIdx != null) {
			FileDto fileDto = cupService.getFileInfoByIdx(fileIdx);
			cupService.deleteFileByIdx(fileDto);
		}
		
		return "redirect:/admin/cup/discardCupManage";
	}
	
	//컵 수정 화면 
	@PostMapping("/cupStateModify")
	public String cupStateModify (Cup cup) {
		log.info("cupStateModify cup:{}", cup);
		cupService.modifyCupState(cup);
		
		return "redirect:admin/cup/cupStateManage";
	}
	//컵 상태 삭제
	@PostMapping("/cupStateRemove")
	public String cupStateRemove (Model model,
								  @RequestParam(name="cupQR") List<String> cupQRArr
								  ) {
		cupService.removeCupState(cupQRArr);

		
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
			 					HttpSession session,
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
		model.addAttribute("adminID", session.getAttribute("SID"));
		
		return "admin/cup/cupStateModify";
	}
	//컵 상태 관리
	@SuppressWarnings("unchecked")
	@GetMapping("/cupStateManage")
	public String cupStateManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
								 @RequestParam (value="searchKey", required= false) String searchKey,
							     @RequestParam (value="searchValue", required= false) String searchValue,
								 Model model) {
		//search 
		
		//required= false 입력값 필수로 안받겠다. defaultValue = "1" 기본값 설정,문자열만 입력 가능 Modle=보내질 데이터
		Map<String,Object> resultMap = cupService.getCupStateList(currentPage,searchKey,searchValue);
		
		List<Map<String,Object>> cupStateList = (List<Map<String,Object>>)resultMap.get("cupStateList");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		int lastPage = (int)resultMap.get("lastPage");
		int rowPerPage = (int) resultMap.get("rowPerPage");
		int cupStateListCount = (int) resultMap.get("cupStateListCount");
		
		model.addAttribute("title", "컵 상태 관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("cupStateListCount",cupStateListCount);
		model.addAttribute("cupStateList", cupStateList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("rowPerPage", rowPerPage);
		return "admin/cup/cupStateManage";
	}
	//컵 출고 관리
	@SuppressWarnings("unchecked")
	@GetMapping("/cupOutManage")
	public String cupOutManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
								Model model) {
		Map<String,Object> resultMap = cupService.getDayOutList(currentPage);
		List<Map<String,Object>> cupDayOutList = (List<Map<String,Object>>)resultMap.get("cupDayOutList");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		int rowPerPage = (int) resultMap.get("rowPerPage");
		int lastPage = (int)resultMap.get("lastPage");
		List<Kiosk>installedKiosk = kioskMapper.getInstalledKioskPartnerList();
		log.info("installedKiosk:{}",installedKiosk.size());
		
		model.addAttribute("title","구구 컵 출고관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("cupDayOutList", cupDayOutList);
		model.addAttribute("installedKiosk", installedKiosk);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("rowPerPage", rowPerPage);
		return "admin/cup/cupOutManage";
	}
	
	
	// 컵 재고 관리
	@SuppressWarnings("unchecked")
	@GetMapping("/cupStockManage")
	public String cupStockManage(@RequestParam(value="currentPage", required = false ,defaultValue = "1")int currentPage,
						Model model) {
		
		
		Map<String,Object> resultMap = cupService.getCupStockList(currentPage);
		int lastPage = (int)resultMap.get("lastPage");
		int rowPerPage = (int)resultMap.get("rowPerPage");
		
		List<Map<String,Object>> cupStockList = (List<Map<String,Object>>) resultMap.get("cupStockList");
		int startPageNum = (int) resultMap.get("startPageNum");
		int endPageNum = (int) resultMap.get("endPageNum");
		model.addAttribute("title","구구 컵 재고 관리");
		model.addAttribute("currentPage", currentPage);
		model.addAttribute("lastPage", lastPage);
		model.addAttribute("cupStockList", cupStockList);
		model.addAttribute("startPageNum", startPageNum);
		model.addAttribute("endPageNum", endPageNum);
		model.addAttribute("rowPerPage", rowPerPage);
		model.addAttribute("finalStock", cupMapper.getFinalStock());
	
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
