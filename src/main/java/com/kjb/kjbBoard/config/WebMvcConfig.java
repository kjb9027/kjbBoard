package com.kjb.kjbBoard.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

	// beforeActionInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("beforeActionInterceptor")
	HandlerInterceptor beforeActionInterceptor;

	// needLoginInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("needLoginInterceptor")
	HandlerInterceptor needLoginInterceptor;

	// needLogoutInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("needLogoutInterceptor")
	HandlerInterceptor needLogoutInterceptor;
	
	// needAdminInterceptor 인터셉터 불러오기
	@Autowired
	@Qualifier("needAdminInterceptor")
	HandlerInterceptor needAdminInterceptor;

	// 이 함수는 인터셉터를 적용하는 역할을 합니다.
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// beforeActionInterceptor 인터셉터가 모든 액션 실행전에 실행되도록 처리
		registry.addInterceptor(beforeActionInterceptor)
				.addPathPatterns("/**").excludePathPatterns("/resource/**")
				.excludePathPatterns("/gen/**");

		// 로그인 필요
		registry.addInterceptor(needLoginInterceptor)
				.addPathPatterns("/usr/**")
				//이 url은 인터셉터가 확인하지 않아도 됨
				.excludePathPatterns("/")
				.excludePathPatterns("/adm/**")
				.excludePathPatterns("/resource/**")
				.excludePathPatterns("/usr/home/**")
				.excludePathPatterns("/usr/member/login")
				.excludePathPatterns("/usr/member/doLogin")
				.excludePathPatterns("/usr/member/join")
				.excludePathPatterns("/usr/member/doJoin")
				.excludePathPatterns("/usr/article/list")
				.excludePathPatterns("/usr/article/detail")
				.excludePathPatterns("/usr/reply/list")
				.excludePathPatterns("/usr/member/findLoginId")
				.excludePathPatterns("/usr/member/doFindLoginId")
				.excludePathPatterns("/usr/member/findLoginPw")
				.excludePathPatterns("/usr/member/doFindLoginPw")
				.excludePathPatterns("/common/**")
				.excludePathPatterns("/usr/file/test*")
				.excludePathPatterns("/usr/file/doTest*")
				.excludePathPatterns("/test/**")
				.excludePathPatterns("/error");
		
		// 관리자 권한 필요
		registry.addInterceptor(needAdminInterceptor)
				.addPathPatterns("/adm/**")
				//이 url은 인터셉터가 확인하지 않아도 됨
				.excludePathPatterns("/adm/member/login")
				.excludePathPatterns("/adm/member/doLogin")
				.excludePathPatterns("/adm/member/join")
				.excludePathPatterns("/adm/member/doJoin");

		// 로그인 상태에서 접속할 수 없는 URI 전부 기술
		registry.addInterceptor(needLogoutInterceptor)
				.addPathPatterns("/adm/member/login")
				.addPathPatterns("/adm/member/doLogin")
				.addPathPatterns("/usr/member/login")
				.addPathPatterns("/usr/member/doLogin")
				.addPathPatterns("/usr/member/join")
				.addPathPatterns("/usr/member/doJoin");
	}
}
