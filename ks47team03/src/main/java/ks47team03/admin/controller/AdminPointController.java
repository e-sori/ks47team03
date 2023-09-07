package ks47team03.admin.controller;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ks47team03.admin.dto.AdminPoint;
import ks47team03.admin.service.AdminPointService;
import ks47team03.user.dto.User;
import ks47team03.user.service.UserCommonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
@AllArgsConstructor
@RequestMapping("/admin/point")
public class AdminPointController {
	
	// 의존성 주입
	private final AdminPointService adminPointService;
	private final UserCommonService userCommonService;
	
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
	
	// 포인트 관련 기준 등록
	@PostMapping("/addPointStandard")
	public String addPointStandard(AdminPoint adminPoint, 
									RedirectAttributes reAttr) {
		
		adminPointService.addPointStandard(adminPoint);
			
		reAttr.addAttribute("message","등록 완료");
			
		return "redirect:/admin/point/pointStandardManage";
	}		
	
	// 포인트 관련 기준 등록 모달 (ajax로 데이터 리턴)
	@GetMapping("/getPointStandardModalList")
	@ResponseBody
	public Map<String,Object> getPointStandardModalList(@RequestParam(value="tableId")String tableId, 
									HttpSession session) {
		
		Map<String,Object> pointStandardModalReusltMap = new HashMap<String,Object>();
		
		String tableDbName = null;		
		
		if(tableId.equals("Max"))	tableDbName = "day_maximum_count";	
		else if(tableId.equals("Expire")) tableDbName = "point_expire_standard";			
		else if(tableId.equals("Save")) tableDbName = "point_save_standard";
		else if(tableId.equals("Refund")) tableDbName = "point_refund_standard";
		else tableDbName = "point_save_use_type";	
		
		String newPointStandardCode = adminPointService.getIncreaseCode(tableDbName);
		
		String SID = (String) session.getAttribute("SID");		
		User adminInfo = userCommonService.getUserInfoById(SID);		
		String adminPw = adminInfo.getUserPw();
		
		pointStandardModalReusltMap.put("newPointStandardCode", newPointStandardCode);
		pointStandardModalReusltMap.put("SID", SID);
		pointStandardModalReusltMap.put("adminPw", adminPw);
		
		return pointStandardModalReusltMap;
	}		
	// 포인트 관련 기준 관리 화면 (ajax로 데이터 리턴 5. 포인트 타입)
    @GetMapping("/pointTypeStandard")
    @ResponseBody
    public Map<String,Object> getPointTypeStandard(Model model) {        
        Map<String,Object> pointStandardResultMap = adminPointService.getPointTypeStandard();        
        
        return pointStandardResultMap;
    }
    
    // 포인트 관련 기준 관리 화면 (ajax로 데이터 리턴 4. 포인트 환급 기준)
    @GetMapping("/pointRefundStandard")
    @ResponseBody
    public Map<String,Object> getPointRefundStandard(Model model) {        
        Map<String,Object> pointStandardResultMap = adminPointService.getPointRefundStandard();        
        
        return pointStandardResultMap;
    }
    
    // 포인트 관련 기준 관리 화면 (ajax로 데이터 리턴 3. 포인트 적립 기준)
    @GetMapping("/pointSaveStandard")
    @ResponseBody
    public Map<String,Object> getPointSaveStandard(Model model) {        
        Map<String,Object> pointStandardResultMap = adminPointService.getPointSaveStandard();        
        
        return pointStandardResultMap;
    }
	
	// 포인트 관련 기준 관리 화면 (ajax로 데이터 리턴 2. 포인트 만료 기간 기준)
    @GetMapping("/pointExpireStandard")
    @ResponseBody
    public Map<String,Object> getPointExpireStandard(Model model) {        
        Map<String,Object> pointStandardResultMap = adminPointService.getPointExpireStandard();        
        
        return pointStandardResultMap;
    }
	
	// 포인트 관련 기준 관리 화면	(ajax로 데이터 리턴 1. 하루 최대 적립 포인트 횟수 기준)
	@GetMapping("/pointMaxStandard")
	@ResponseBody
	public Map<String,Object> getPointMaxStandard(Model model) {		
		Map<String,Object> pointStandardResultMap = adminPointService.getPointMaxStandard();		
		
		return pointStandardResultMap;
	}

	// 포인트 관련 기준 관리 화면	
	@GetMapping("/pointStandardManage")
	@SuppressWarnings("unchecked")
	public String pointStandardManage(@RequestParam(value="message", required = false) String message,
										Model model) {		
		Map<String,Object> pointStandardResultMap = adminPointService.getPointMaxStandard();
		
		List<Map<String,Object>>  pointStandardList = (List<Map<String,Object>> )pointStandardResultMap.get("pointStandardList");
		List<Map<String,Object>> codeUseList = (List<Map<String,Object>>) pointStandardResultMap.get("codeUseList");
		int useMaxCount = (int)pointStandardResultMap.get("useMaxCount");
			
		model.addAttribute("useMaxCount", useMaxCount); 
		model.addAttribute("title","포인트 관련 기준 관리");
		model.addAttribute("codeUseList", codeUseList);
		model.addAttribute("pointStandardList", pointStandardList);	
		if(message != null) model.addAttribute("message", message);
		
		return "admin/point/pointStandardManage";
	}

}
