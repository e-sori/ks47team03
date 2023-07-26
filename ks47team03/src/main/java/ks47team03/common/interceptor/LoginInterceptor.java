package ks47team03.common.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor{
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();
		String sessionId = (String) session.getAttribute("SID");
		log.info("sessionId:{}" , sessionId);
		if(sessionId == null) {
			response.sendRedirect("/projectIntro");
			return false;
		}
		else {
			String requestUri = request.getRequestURI();
			String sessionLevel = (String) session.getAttribute("SLEVEL");
			
			if(sessionLevel.equals("설치사업장")) {
				if(		requestUri.indexOf("/admin/") > -1
					||	requestUri.indexOf("/partner/washDiscardCup") > -1
					||	requestUri.indexOf("/partner/businessMyKioskList") > -1
					||	requestUri.indexOf("/partner/washDiscardCup") > -1
					||	requestUri.indexOf("/deposit") > -1
					||	requestUri.indexOf("/myPoint") 	 > -1 ) {
					response.sendRedirect("/projectIntro");
					return false;
				}
			}
			if(sessionLevel.equals("무인기기설치업체")) {
				if(		requestUri.indexOf("/admin/") > -1
					||	requestUri.indexOf("/partner/businessKioskApply") > -1
					||	requestUri.indexOf("/partner/businessKioskApplyResult") 	 > -1
					||	requestUri.indexOf("/partner/kioskInstalledList") 	 > -1 
					||	requestUri.indexOf("/partner/businessAddCup") 	 > -1 
					||	requestUri.indexOf("/partner/washDiscardCup") 	 > -1 
					||	requestUri.indexOf("/deposit") 	 > -1 
					||	requestUri.indexOf("/myPoint") 	 > -1 ) {
					response.sendRedirect("/projectIntro");
					return false;
				}
			}
			if(sessionLevel.equals("세척업체")) {
				if(		requestUri.indexOf("/admin/") > -1
					||	requestUri.indexOf("/partner/businessKioskApply") > -1
					||	requestUri.indexOf("/partner/businessKioskApplyResult") 	 > -1
					||	requestUri.indexOf("/partner/kioskInstalledList") 	 > -1 
					||	requestUri.indexOf("/partner/businessAddCup") 	 > -1 
					||	requestUri.indexOf("/partner/businessMyKioskList") 	 > -1 
					||	requestUri.indexOf("/deposit") 	 > -1 
					||	requestUri.indexOf("/myPoint") 	 > -1 ) {
					response.sendRedirect("/projectIntro");
					return false;
				}
			}
			if(sessionLevel.equals("일반사용자")) {
				if(		requestUri.indexOf("/admin/") > -1
					||	requestUri.indexOf("/partner") > -1
					||	requestUri.indexOf("/partner/businessKioskApplyResult") 	 > -1
					||	requestUri.indexOf("/partner/kioskInstalledList") 	 > -1
					||	requestUri.indexOf("/partner/businessAddCup") 	 > -1
					||	requestUri.indexOf("/partner/washDiscardCup") 	 > -1
					||	requestUri.indexOf("/partner/partnerInfo") 	 > -1 ) {
					response.sendRedirect("/projectIntro");
					return false;
				}
			}
		}
		
		
		return true;
	}
}
