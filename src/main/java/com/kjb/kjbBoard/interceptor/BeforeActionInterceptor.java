package com.kjb.kjbBoard.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kjb.kjbBoard.dto.Member;
import com.kjb.kjbBoard.service.MemberService;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	private MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Member loginedMember = null;
		int loginedMemberId = 0;

		String authKey = request.getParameter("authKey");
		if (authKey != null && authKey.length() > 0) {
			loginedMember = memberService.getMemberByauthKey(authKey);
			if (loginedMember == null) {
				request.setAttribute("authKeyStatus", "invalid");// 유효하지않다
			} else {
				request.setAttribute("authKeyStatus", "valid");// 유효하다
				loginedMemberId= loginedMember.getId();
			}
		} else {
			HttpSession session = request.getSession();
			request.setAttribute("authKeyStatus", "none");
			if(session.getAttribute("loginedMemberId") != null) {
				loginedMemberId = (int) session.getAttribute("loginedMemberId");
				loginedMember = memberService.getMember(loginedMemberId);
			}
		}
		
		// 로그인 여부에 관련된 정보를 request에 담는다.
		boolean isLogined = false;
		boolean isAdmin = false;

		if (loginedMember != null) {
			isLogined = true;
			isAdmin = memberService.isAdmin(loginedMemberId);
		}

		request.setAttribute("loginedMemberId", loginedMemberId);
		request.setAttribute("isLogined", isLogined);
		request.setAttribute("isAdmin", isAdmin);
		request.setAttribute("loginedMember", loginedMember);

		return HandlerInterceptor.super.preHandle(request, response, handler);
	}
}
