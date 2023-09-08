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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	// 세션 아이디 리턴
	@GetMapping("/returnSessionId")
	@ResponseBody
	public String openSessionId(HttpSession session) {
	    String SID = (String) session.getAttribute("SID");      
	    return SID;
	}
	
	// 포인트 관련 기준 삭제 처리 (5. 포인트 타입)
    @DeleteMapping("/pointStandardManage/tabType/delete")
    @ResponseBody
    public String deletePointTypeStandard(@RequestBody AdminPoint adminPoint) {
        String message = null;
        List<String> codeList = adminPoint.getCodeList();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertAdminPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertAdminPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "삭제가 완료되었습니다.";
            adminPointService.deletePointTypeStandard(codeList);
        }else {
            message =  "비밀번호가 일치하지 않습니다.";
        }         

        return message;
    }
    
 // 포인트 관련 기준 삭제 처리 (4. 포인트 환급 기준)
    @DeleteMapping("/pointStandardManage/tabRefund/delete")
    @ResponseBody
    public String deletePointRefundStandard(@RequestBody AdminPoint adminPoint) {
        String message = null;
        List<String> codeList = adminPoint.getCodeList();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertAdminPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertAdminPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "삭제가 완료되었습니다.";
            adminPointService.deletePointRefundStandard(codeList);
        }else {
            message =  "비밀번호가 일치하지 않습니다.";
        }         

        return message;
    }
    
 // 포인트 관련 기준 삭제 처리 (3. 포인트 적립 기준)
    @DeleteMapping("/pointStandardManage/tabSave/delete")
    @ResponseBody
    public String deletePointSaveStandard(@RequestBody AdminPoint adminPoint) {
        String message = null;
        List<String> codeList = adminPoint.getCodeList();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertAdminPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertAdminPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "삭제가 완료되었습니다.";
            adminPointService.deletePointSaveStandard(codeList);
        }else {
            message =  "비밀번호가 일치하지 않습니다.";
        }         

        return message;
    }
    
 // 포인트 관련 기준 삭제 처리  (2. 포인트 만료 기간 기준)
    @DeleteMapping("/pointStandardManage/tabExpire/delete")
    @ResponseBody
    public String deletePointExpireStandard(@RequestBody AdminPoint adminPoint) {
        String message = null;
        List<String> codeList = adminPoint.getCodeList();
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertAdminPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertAdminPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "삭제가 완료되었습니다.";
            adminPointService.deletePointExpireStandard(codeList);
        }else {
            message =  "비밀번호가 일치하지 않습니다.";
        }         

        return message;
    }
	
	// 포인트 관련 기준 삭제 처리 (1. 하루 최대 적립 포인트 횟수 기준)
	@DeleteMapping("/pointStandardManage/tabMax/delete")
	@ResponseBody
	public String deletePointMaxStandard(@RequestBody AdminPoint adminPoint) {
	    String message = null;
	    List<String> codeList = adminPoint.getCodeList();
	    
	    // 비밀번호 일치 검사
	    String adminId = adminPoint.getAdminId();
	    String insertAdminPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertAdminPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "삭제가 완료되었습니다.";
            adminPointService.deletePointMaxStandard(codeList);
        }else {
            message =  "비밀번호가 일치하지 않습니다.";
        }         

	    return message;
	}
	
	// 포인트 관련 기준 수정 처리 (5. 포인트 타입)
    @PutMapping("/pointStandardManage/tabType/modify/{code}")
    @ResponseBody
    public String modifyPointTypeStandard(AdminPoint adminPoint) {
        String message = null;
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "수정이 완료되었습니다.";
            adminPointService.modifyPointTypeStandard(adminPoint);
        }else {
            message =  "비밀번호가 일치하지 않습니다.";
        }         

        return message;
    }
    
 // 포인트 관련 기준 수정 처리 (4. 포인트 환급 기준)
    @PutMapping("/pointStandardManage/tabRefund/modify/{code}")
    @ResponseBody
    public String modifyPointRefundStandard(AdminPoint adminPoint) {
        String message = null;
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "수정이 완료되었습니다.";
            adminPointService.modifyPointRefundStandard(adminPoint);
        }else {
            message =  "비밀번호가 일치하지 않습니다.";
        }         
        
        return message;
    }
    
 // 포인트 관련 기준 수정 처리 (3. 포인트 적립 기준)
    @PutMapping("/pointStandardManage/tabSave/modify/{code}")
    @ResponseBody
    public String modifyPointSaveStandard(AdminPoint adminPoint) {
        String message = null;
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "수정이 완료되었습니다.";
            adminPointService.modifyPointSaveStandard(adminPoint);
        }else {
            message =  "비밀번호가 일치하지 않습니다.";
        }         
        
        return message;
    }
    
 // 포인트 관련 기준 수정 처리 (2. 포인트 만료 기간 기준)
    @PutMapping("/pointStandardManage/tabExpire/modify/{code}")
    @ResponseBody
    public String modifyPointExpireStandard(AdminPoint adminPoint) {
        String message = null;
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "수정이 완료되었습니다.";
            adminPointService.modifyPointExpireStandard(adminPoint);
        }else {
            message =  "비밀번호가 일치하지 않습니다.";
        }         
        
        return message;
    }
	
	// 포인트 관련 기준 수정 처리 (1. 하루 최대 적립 포인트 횟수 기준)
    @PutMapping("/pointStandardManage/tabMax/modify/{code}")
    @ResponseBody
    public String modifyPointMaxStandard(AdminPoint adminPoint) {
        String message = null;
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "수정이 완료되었습니다.";
            adminPointService.modifyPointMaxStandard(adminPoint);
        }else {
            message =  "비밀번호가 일치하지 않습니다.";
        }         
        
        return message;
    }
	
	// 포인트 관련 기준 수정 모달 화면 (5. 포인트 타입)
    @GetMapping("/pointStandardManage/tabType/modify/{code}")
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
    @GetMapping("/pointStandardManage/tabRefund/modify/{code}")
    @ResponseBody
    public AdminPoint openPointRefundStandardModifyModal(@PathVariable(value="code") String code, HttpSession session) {
        String SID = (String) session.getAttribute("SID");      
        
        AdminPoint getPointStandardDetail = adminPointService.getPointRefundStandardDetail(code);
        getPointStandardDetail.setAdminId(SID);
        
        return getPointStandardDetail;
    }
    
    // 포인트 관련 기준 수정 모달 화면 (3. 포인트 적립 기준)
    @GetMapping("/pointStandardManage/tabSave/modify/{code}")
    @ResponseBody
    public AdminPoint openPointSaveStandardModifyModal(@PathVariable(value="code") String code, HttpSession session) {
        String SID = (String) session.getAttribute("SID");      
        
        AdminPoint getPointStandardDetail = adminPointService.getPointSaveStandardDetail(code);
        getPointStandardDetail.setAdminId(SID);
        
        return getPointStandardDetail;
    }
    
    // 포인트 관련 기준 수정 모달 화면 (2. 포인트 만료 기간 기준)
    @GetMapping("/pointStandardManage/tabExpire/modify/{code}")
    @ResponseBody
    public AdminPoint openPointExpireStandardModifyModal(@PathVariable(value="code") String code, HttpSession session) {
        String SID = (String) session.getAttribute("SID");      
        
        AdminPoint getPointStandardDetail = adminPointService.getPointExpireStandardDetail(code);
        getPointStandardDetail.setAdminId(SID);
        
        return getPointStandardDetail;
    }
	
	// 포인트 관련 기준 수정 모달 화면 (1. 하루 최대 적립 포인트 횟수 기준)
	@GetMapping("/pointStandardManage/tabMax/modify/{code}")
	@ResponseBody
	public AdminPoint openPointMaxStandardModifyModal(@PathVariable(value="code") String code, HttpSession session) {
	    String SID = (String) session.getAttribute("SID");      
        
	    AdminPoint getPointStandardDetail = adminPointService.getPointMaxStandardDetail(code);
        getPointStandardDetail.setAdminId(SID);
        log.info("sfdsfsdfsdfsdf:{}",getPointStandardDetail);

        return getPointStandardDetail;
	}
	
	// 포인트 관련 기준 등록 처리 (5. 포인트 타입)
    @PostMapping("/pointStandardManage/tabType/write")
    @ResponseBody
    public String addPointTypeStandard(AdminPoint adminPoint) {        
        String message = null;
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
        log.info("fsdffsdfs:{}",adminPw);
        int checkPwResult = adminPointService.checkPw(insertPw, adminPw);
        log.info("fsdffsdfs:{}",checkPwResult);
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "등록이 완료되었습니다.";
            adminPointService.addPointTypeStandard(adminPoint);
        }else {
            message = "비밀번호가 일치하지 않습니다.";
        }               
        
        return message;
    }   
    
 // 포인트 관련 기준 등록 처리 (4. 포인트 환급 기준)
    @PostMapping("/pointStandardManage/tabRefund/write")
    @ResponseBody
    public String addPointRefundStandard(AdminPoint adminPoint) {        
        String message = null;
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "등록이 완료되었습니다.";
            adminPointService.addPointRefundStandard(adminPoint);
        }else {
            message = "비밀번호가 일치하지 않습니다.";
        }               
        
        return message;
    }   
    
 // 포인트 관련 기준 등록 처리 (3. 포인트 적립 기준)
    @PostMapping("/pointStandardManage/tabSave/write")
    @ResponseBody
    public String addPointSaveStandard(AdminPoint adminPoint) {        
        String message = null;
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "등록이 완료되었습니다.";
            adminPointService.addPointSaveStandard(adminPoint);
        }else {
            message = "비밀번호가 일치하지 않습니다.";
        }               
        
        return message;
    }   
    
 // 포인트 관련 기준 등록 처리 (2. 포인트 만료 기간 기준)
    @PostMapping("/pointStandardManage/tabExpire/write")
    @ResponseBody
    public String addPointExpireStandard(AdminPoint adminPoint) {        
        String message = null;
        
        // 비밀번호 일치 검사
        String adminId = adminPoint.getAdminId();
        String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
          
        int checkPwResult = adminPointService.checkPw(insertPw, adminPw);
        
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "등록이 완료되었습니다.";
            adminPointService.addPointExpireStandard(adminPoint);
        }else {
            message = "비밀번호가 일치하지 않습니다.";
        }               
        
        return message;
    }   
	
	// 포인트 관련 기준 등록 처리 (1. 하루 최대 적립 포인트 횟수 기준)
	@PostMapping("/pointStandardManage/tabMax/write")
	@ResponseBody
	public String addPointMaxStandard(AdminPoint adminPoint) {				
		String message = null;
	    
	    // 비밀번호 일치 검사
		String adminId = adminPoint.getAdminId();
		String insertPw = adminPoint.getInsertAdminPw();
        User userInfo = adminCommonService.getUserInfoByID(adminId);
        String adminPw = userInfo.getUserPw();
        
        int checkPwResult = adminPointService.checkPw(insertPw, adminPw);
          
        // 일치 여부에 따른 로직
        final int PWCORRESPOND = 1;
        
        if(checkPwResult == PWCORRESPOND) {
            message = "등록이 완료되었습니다.";
            adminPointService.addPointMaxStandard(adminPoint);
        }else {
            message = "비밀번호가 일치하지 않습니다.";
        }				
        
		return message;
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
