package ks47team03.common.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;


import ks47team03.common.interceptor.LoggerInterceptor;
import ks47team03.common.interceptor.LoginInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	//파일 업로드
	@Value("${files.path}")
	private String filePath;

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
		excludePathList.add("/error");
		
		registry.addInterceptor(loggerInterceptor)
				.addPathPatterns("/**")
				.excludePathPatterns("/favicon.ico")
				.excludePathPatterns(excludePathList);
		
		registry.addInterceptor(loginInterceptor)
		.addPathPatterns("/**")
		.excludePathPatterns("/favicon.ico")
		.excludePathPatterns("/")
		.excludePathPatterns("/projectIntro")
		.excludePathPatterns("/sori")
		.excludePathPatterns("/sori/adminLogin")
		.excludePathPatterns("/sori/userLogin")
		.excludePathPatterns("/hyeonjeong")
		.excludePathPatterns("/admin/")
		.excludePathPatterns("/login")
		.excludePathPatterns("/logout")
		.excludePathPatterns("/join")
		.excludePathPatterns("/user/join")
		.excludePathPatterns("/idCheck")
		.excludePathPatterns("/kiosk/kioskPlace")
		.excludePathPatterns(excludePathList);

	}
	/**
	 * 주소요청에 따른 외부파일 경로 설정
	 * git push 할 땐 c:지워서 올려야됨
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**")
				.addResourceLocations("file:///" + filePath + "resources/")
				.setCachePeriod(3600)
				.resourceChain(true)
				.addResolver(new PathResourceResolver());
		WebMvcConfigurer.super.addResourceHandlers(registry);
	}
	
}
