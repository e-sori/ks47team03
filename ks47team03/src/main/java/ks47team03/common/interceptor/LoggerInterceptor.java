package ks47team03.common.interceptor;

import java.util.Set;
import java.util.StringJoiner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class LoggerInterceptor implements HandlerInterceptor{
	
	private static final Logger log = LoggerFactory.getLogger(LoggerInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		Set<String> paramkeySet = request.getParameterMap().keySet();
		
		StringJoiner param = new StringJoiner(", ");
		
		for(String key : paramkeySet) {
			//memberId=id001 -> memberId: id001
			//memberId=id001&memberPw=pw001 -> memberId: id001, memberPw: pw001
			param.add(key + ": " + request.getParameter(key));
		}
		
		log.info("ACCESS INFO======================================================");
		log.info("PORT		::::::		{}", request.getLocalPort());
		log.info("ServerName		::::::		{}", request.getServerName());
		log.info("HTTPMethod		::::::		{}", request.getMethod());
		log.info("URI			::::::		{}", request.getRequestURI());
		log.info("CLIENT IP		::::::		{}", request.getRemoteAddr());
		log.info("Parameter		::::::		{}", param);
		log.info("ACCESS INFO======================================================");
		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
