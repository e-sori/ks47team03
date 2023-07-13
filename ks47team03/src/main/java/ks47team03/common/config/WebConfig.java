package ks47team03.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import ks47team03.common.interceptor.LoggerInterceptor;
import ks47team03.common.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{

	private final LoggerInterceptor loggerInterceptor;
	private final LoginInterceptor loginInterceptor;
	
	
	
	public WebConfig(LoggerInterceptor loggerInterceptor, LoginInterceptor loginInterceptor) {
		this.loggerInterceptor = loggerInterceptor;
		this.loginInterceptor = loginInterceptor;	  
	}
	 	 
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		List<String> excludePathList = new ArrayList<String>();
		excludePathList.add("/admin/assets/**");
		excludePathList.add("/admin/css/**");
		excludePathList.add("/admin/data/**");
		excludePathList.add("/admin/fonts/**");
		excludePathList.add("/admin/js/**");
		excludePathList.add("/user/css/**");
		excludePathList.add("/user/img/**");
		excludePathList.add("/user/js/**");
		excludePathList.add("/user/scss/**");
		excludePathList.add("/user/vendors/**");
		
		
		registry.addInterceptor(loggerInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns(excludePathList);
		
		registry.addInterceptor(loginInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns("/")
				.excludePathPatterns("/login")
//				.excludePathPatterns("/logout")
//				.excludePathPatterns("/addMember")
//				.excludePathPatterns("/idCheck")
				.excludePathPatterns(excludePathList);
		
		WebMvcConfigurer.super.addInterceptors(registry);
	}
	
}
