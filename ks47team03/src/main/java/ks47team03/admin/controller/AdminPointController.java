package ks47team03.admin.controller;

import jakarta.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import ks47team03.admin.dto.AdminPoint;
import ks47team03.admin.service.AdminCommonService;
import ks47team03.admin.service.AdminPointService;
import ks47team03.user.dto.DepositStandard;
import ks47team03.user.dto.User;
import ks47team03.user.service.UserCommonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/admin/point")
public class AdminPointController {
	
	// 의존성 주입
	private final AdminPointService adminPointService;
	private final UserCommonService userCommonService;
	private final AdminCommonService adminCommonService;
	
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
	
	// 포인트 관련 기준 수정 처리 (5. 포인트 타입)
    @PutMapping("/pointStandardManage/tabType/{code}")
    @ResponseBody
    public Map<String,Object> modifyPointTypeStandard(AdminPoint adminPoint) {
        Map<String,Object> resultMap = new HashMap<>();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        if(insertPw.equals(adminPw)) {
            resultMap.put("message","수정이 완료되었습니다.");
            adminPointService.modifyPointTypeStandard(adminPoint);
        }else {
            resultMap.put("message","비밀번호가 일치하지 않습니다.");
        }         
        
        return resultMap;
    }
    
 // 포인트 관련 기준 수정 처리 (4. 포인트 환급 기준)
    @PutMapping("/pointStandardManage/tabRefund/{code}")
    @ResponseBody
    public Map<String,Object> modifyPointRefundStandard(AdminPoint adminPoint) {
        Map<String,Object> resultMap = new HashMap<>();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        if(insertPw.equals(adminPw)) {
            resultMap.put("message","수정이 완료되었습니다.");
            adminPointService.modifyPointRefundStandard(adminPoint);
        }else {
            resultMap.put("message","비밀번호가 일치하지 않습니다.");
        }         
        
        return resultMap;
    }
    
 // 포인트 관련 기준 수정 처리 (3. 포인트 적립 기준)
    @PutMapping("/pointStandardManage/tabSave/{code}")
    @ResponseBody
    public Map<String,Object> modifyPointSaveStandard(AdminPoint adminPoint) {
        Map<String,Object> resultMap = new HashMap<>();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        if(insertPw.equals(adminPw)) {
            resultMap.put("message","수정이 완료되었습니다.");
            adminPointService.modifyPointSaveStandard(adminPoint);
        }else {
            resultMap.put("message","비밀번호가 일치하지 않습니다.");
        }         
        
        return resultMap;
    }
    
 // 포인트 관련 기준 수정 처리 (2. 포인트 만료 기간 기준)
    @PutMapping("/pointStandardManage/tabExpire/{code}")
    @ResponseBody
    public Map<String,Object> modifyPointExpireStandard(AdminPoint adminPoint) {
        Map<String,Object> resultMap = new HashMap<>();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        if(insertPw.equals(adminPw)) {
            resultMap.put("message","수정이 완료되었습니다.");
            adminPointService.modifyPointExpireStandard(adminPoint);
        }else {
            resultMap.put("message","비밀번호가 일치하지 않습니다.");
        }         
        
        return resultMap;
    }
	
	// 포인트 관련 기준 수정 처리 (1. 하루 최대 적립 포인트 횟수 기준)
    @PutMapping("/pointStandardManage/tabMax/{code}")
    @ResponseBody
    public Map<String,Object> modifyPointMaxStandard(AdminPoint adminPoint) {
        Map<String,Object> resultMap = new HashMap<>();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        if(insertPw.equals(adminPw)) {
            resultMap.put("message","수정이 완료되었습니다.");
            adminPointService.modifyPointMaxStandard(adminPoint);
        }else {
            resultMap.put("message","비밀번호가 일치하지 않습니다.");
        }         
        
        return resultMap;
    }
	
	// 포인트 관련 기준 수정 모달 화면 (5. 포인트 타입)
    @GetMapping("/pointStandardManage/tabType/{code}")
    @ResponseBody
    public AdminPoint openPointTypeStandardModifyModal(@PathVariable(value="code") String code, HttpSession session) {
        String SID = (String) session.getAttribute("SID");      
        
        List<String> pointTypeGroup = adminPointService.getAllPointType();
        AdminPoint getPointStandardDetail = adminPointService.getPointTypeStandardDetail(code);
        getPointStandardDetail.setAdminId(SID);
        getPointStandardDetail.setPointTypeGroup(pointTypeGroup);
        
        log.info("abcdfef:{}",getPointStandardDetail);
        
        return getPointStandardDetail;
    }
    
    // 포인트 관련 기준 수정 모달 화면 (4. 포인트 환급 기준)
    @GetMapping("/pointStandardManage/tabRefund/{code}")
    @ResponseBody
    public AdminPoint openPointRefundStandardModifyModal(@PathVariable(value="code") String code, HttpSession session) {
        String SID = (String) session.getAttribute("SID");      
        
        AdminPoint getPointStandardDetail = adminPointService.getPointRefundStandardDetail(code);
        getPointStandardDetail.setAdminId(SID);
        
        return getPointStandardDetail;
    }
    
    // 포인트 관련 기준 수정 모달 화면 (3. 포인트 적립 기준)
    @GetMapping("/pointStandardManage/tabSave/{code}")
    @ResponseBody
    public AdminPoint openPointSaveStandardModifyModal(@PathVariable(value="code") String code, HttpSession session) {
        String SID = (String) session.getAttribute("SID");      
        
        AdminPoint getPointStandardDetail = adminPointService.getPointSaveStandardDetail(code);
        getPointStandardDetail.setAdminId(SID);
        
        return getPointStandardDetail;
    }
    
    // 포인트 관련 기준 수정 모달 화면 (2. 포인트 만료 기간 기준)
    @GetMapping("/pointStandardManage/tabExpire/{code}")
    @ResponseBody
    public AdminPoint openPointExpireStandardModifyModal(@PathVariable(value="code") String code, HttpSession session) {
        String SID = (String) session.getAttribute("SID");      
        
        AdminPoint getPointStandardDetail = adminPointService.getPointExpireStandardDetail(code);
        getPointStandardDetail.setAdminId(SID);
        
        return getPointStandardDetail;
    }
	
	// 포인트 관련 기준 수정 모달 화면 (1. 하루 최대 적립 포인트 횟수 기준)
	@GetMapping("/pointStandardManage/tabMax/{code}")
	@ResponseBody
	public AdminPoint openPointMaxStandardModifyModal(@PathVariable(value="code") String code, HttpSession session) {
	    String SID = (String) session.getAttribute("SID");      
        
	    AdminPoint getPointStandardDetail = adminPointService.getPointMaxStandardDetail(code);
        getPointStandardDetail.setAdminId(SID);
        
        return getPointStandardDetail;
	}
	
	// 포인트 관련 기준 등록 처리 (5. 포인트 타입)
    @PostMapping("/pointStandardManage/tabType/write")
    @ResponseBody
    public Map<String,Object> addPointTypeStandard(AdminPoint adminPoint) {        
        Map<String,Object> resultMap = new HashMap<>();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        if(insertPw.equals(adminPw)) {
            resultMap.put("message","등록이 완료되었습니다.");
            adminPointService.addPointTypeStandard(adminPoint);
        }else {
            resultMap.put("message","비밀번호가 일치하지 않습니다.");
        }               
        
        return resultMap;
    }   
    
 // 포인트 관련 기준 등록 처리 (4. 포인트 환급 기준)
    @PostMapping("/pointStandardManage/tabRefund/write")
    @ResponseBody
    public Map<String,Object> addPointRefundStandard(AdminPoint adminPoint) {        
        Map<String,Object> resultMap = new HashMap<>();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        if(insertPw.equals(adminPw)) {
            resultMap.put("message","등록이 완료되었습니다.");
            adminPointService.addPointRefundStandard(adminPoint);
        }else {
            resultMap.put("message","비밀번호가 일치하지 않습니다.");
        }               
        
        return resultMap;
    }   
    
 // 포인트 관련 기준 등록 처리 (3. 포인트 적립 기준)
    @PostMapping("/pointStandardManage/tabSave/write")
    @ResponseBody
    public Map<String,Object> addPointSaveStandard(AdminPoint adminPoint) {        
        Map<String,Object> resultMap = new HashMap<>();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        if(insertPw.equals(adminPw)) {
            resultMap.put("message","등록이 완료되었습니다.");
            adminPointService.addPointSaveStandard(adminPoint);
        }else {
            resultMap.put("message","비밀번호가 일치하지 않습니다.");
        }               
        
        return resultMap;
    }   
    
 // 포인트 관련 기준 등록 처리 (2. 포인트 만료 기간 기준)
    @PostMapping("/pointStandardManage/tabExpire/write")
    @ResponseBody
    public Map<String,Object> addPointExpireStandard(AdminPoint adminPoint) {        
        Map<String,Object> resultMap = new HashMap<>();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        if(insertPw.equals(adminPw)) {
            resultMap.put("message","등록이 완료되었습니다.");
            adminPointService.addPointExpireStandard(adminPoint);
        }else {
            resultMap.put("message","비밀번호가 일치하지 않습니다.");
        }               
        
        return resultMap;
    }   
	
	// 포인트 관련 기준 등록 처리 (1. 하루 최대 적립 포인트 횟수 기준)
	@PostMapping("/pointStandardManage/tabMax/write")
	@ResponseBody
	public Map<String,Object> addPointMaxStandard(AdminPoint adminPoint) {		
		Map<String,Object> resultMap = new HashMap<>();
		
		// 비밀번호 일치 검사
		String adminId = adminPoint.getAdminId();
		String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        if(insertPw.equals(adminPw)) {
            resultMap.put("message","등록이 완료되었습니다.");
            adminPointService.addPointMaxStandard(adminPoint);
        }else {
            resultMap.put("message","비밀번호가 일치하지 않습니다.");
        }				
        
		return resultMap;
	}		
	
	// 포인트 관련 기준 등록 모달 화면 (5. 포인트 타입)
    @GetMapping("/pointStandardManage/tabType/write")
    @ResponseBody
    public Map<String,Object> openPointTypeStandardWirteModal(HttpSession session) {
        
        Map<String,Object> pointStandardModalReusltMap = new HashMap<String,Object>();
        
        String newPointStandardCode = adminPointService.getIncreaseCode("point_save_use_type");      
        String SID = (String) session.getAttribute("SID");         
        
        pointStandardModalReusltMap.put("newPointStandardCode", newPointStandardCode);
        pointStandardModalReusltMap.put("SID", SID);
        
        return pointStandardModalReusltMap;
    }       
    
 // 포인트 관련 기준 등록 모달 화면 (4. 포인트 환급 기준)
    @GetMapping("/pointStandardManage/tabRefund/write")
    @ResponseBody
    public Map<String,Object> openPointRefundStandardWirteModal(HttpSession session) {
        
        Map<String,Object> pointStandardModalReusltMap = new HashMap<String,Object>();
        
        String newPointStandardCode = adminPointService.getIncreaseCode("point_refund_standard");       
        String SID = (String) session.getAttribute("SID");      

        pointStandardModalReusltMap.put("newPointStandardCode", newPointStandardCode);
        pointStandardModalReusltMap.put("SID", SID);
        
        return pointStandardModalReusltMap;
    }       
	
	// 포인트 관련 기준 등록 모달 화면 (3. 포인트 적립 기준)
    @GetMapping("/pointStandardManage/tabSave/write")
    @ResponseBody
    public Map<String,Object> openPointStandardWirteModal(HttpSession session) {
        
        Map<String,Object> pointStandardModalReusltMap = new HashMap<String,Object>();
        
        String newPointStandardCode = adminPointService.getIncreaseCode("point_save_standard");       
        String SID = (String) session.getAttribute("SID");      
        
        pointStandardModalReusltMap.put("newPointStandardCode", newPointStandardCode);
        pointStandardModalReusltMap.put("SID", SID);
        
        return pointStandardModalReusltMap;
    }       
	
	// 포인트 관련 기준 등록 모달 화면 (2. 포인트 만료 기간 기준)
    @GetMapping("/pointStandardManage/tabExpire/write")
	@ResponseBody
	public Map<String,Object> openPointExpireStandardWirteModal(HttpSession session) {
		Map<String,Object> pointStandardModalReusltMap = new HashMap<String,Object>();
		
		String newPointStandardCode = adminPointService.getIncreaseCode("point_expire_standard");		
		String SID = (String) session.getAttribute("SID");		
		
		pointStandardModalReusltMap.put("newPointStandardCode", newPointStandardCode);
		pointStandardModalReusltMap.put("SID", SID);
		
		return pointStandardModalReusltMap;
	}		
	
	// 포인트 관련 기준 등록 모달 화면 (1. 하루 최대 적립 포인트 횟수 기준)
    @GetMapping("/pointStandardManage/tabMax/write")
    @ResponseBody
    public Map<String,Object> openPointMaxStandardWirteModal(HttpSession session) {
        Map<String,Object> pointStandardModalReusltMap = new HashMap<String,Object>();
        
        String newPointStandardCode = adminPointService.getIncreaseMaxCode();       
        String SID = (String) session.getAttribute("SID");      
        
        pointStandardModalReusltMap.put("newPointStandardCode", newPointStandardCode);
        pointStandardModalReusltMap.put("SID", SID);
        
        return pointStandardModalReusltMap;
    }       
    
	// 포인트 관련 기준 관리 화면 (클라이언트로로 데이터 리턴 5. 포인트 타입)
    @GetMapping("/pointStandardManage/tabType")
    @ResponseBody
    public Map<String,Object> openPointTypeStandard(Model model, HttpSession session) {        
        Map<String,Object> pointStandardResultMap = adminPointService.getPointTypeStandard();    
        
        return pointStandardResultMap;
    }
    
    // 포인트 관련 기준 관리 화면 (클라이언트로로 데이터 리턴 4. 포인트 환급 기준)
    @GetMapping("/pointStandardManage/tabRefund")
    @ResponseBody
    public Map<String,Object> openPointRefundStandard(Model model) {        
        Map<String,Object> pointStandardResultMap = adminPointService.getPointRefundStandard();        
        
        return pointStandardResultMap;
    }
    
    // 포인트 관련 기준 관리 화면 (클라이언트로로 데이터 리턴 3. 포인트 적립 기준)
    @GetMapping("/pointStandardManage/tabSave")
    @ResponseBody
    public Map<String,Object> openPointSaveStandard(Model model) {        
        Map<String,Object> pointStandardResultMap = adminPointService.getPointSaveStandard();        
        
        return pointStandardResultMap;
    }
	
	// 포인트 관련 기준 관리 화면 (클라이언트로로 데이터 리턴 2. 포인트 만료 기간 기준)
    @GetMapping("/pointStandardManage/tabExpire")
    @ResponseBody
    public Map<String,Object> openPointExpireStandard(Model model) {        
        Map<String,Object> pointStandardResultMap = adminPointService.getPointExpireStandard();        
        
        return pointStandardResultMap;
    }
	
	// 포인트 관련 기준 관리 화면	(클라이언트로 데이터 리턴 1. 하루 최대 적립 포인트 횟수 기준)
	@GetMapping("/pointStandardManage/tabMax")
	@ResponseBody
	public Map<String,Object> openPointMaxStandard(Model model) {		
		Map<String,Object> pointStandardResultMap = adminPointService.getPointMaxStandard();		
		
		return pointStandardResultMap;
	}

	// 포인트 관련 기준 관리 화면	
	@GetMapping("/pointStandardManage")
	@SuppressWarnings("unchecked")
	public String openPointStandardManage(@RequestParam(value="message", required = false) String message,
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
