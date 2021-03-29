package com.kjb.kjbBoard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kjb.kjbBoard.dto.Member;
import com.kjb.kjbBoard.service.MemberService;

import lombok.extern.slf4j.Slf4j;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
@Slf4j
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	private MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		HttpSession session = request.getSession();

		// 로그인 여부에 관련된 정보를 request에 담는다.
		boolean isLogined = false;
		boolean isAdmin = false;
		int loginedMemberId = 0;
		Member loginedMember = null;

		if (session.getAttribute("loginedMemberId") != null) { // 만약 세션에 아이디가 저장되있다면
			loginedMemberId = (int) session.getAttribute("loginedMemberId");
			isLogined = true;
			loginedMember = memberService.getMember(loginedMemberId);
			isAdmin = memberService.isAdmin(loginedMemberId);
		}

		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("loginedMember", loginedMember);

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
