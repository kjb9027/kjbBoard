package com.kjb.kjbBoard.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.kjb.kjbBoard.dto.Member;
import com.kjb.kjbBoard.service.MemberService;
import com.kjb.kjbBoard.util.Util;

@Component("beforeActionInterceptor") // 컴포넌트 이름 설정
public class BeforeActionInterceptor implements HandlerInterceptor {
	@Autowired
	private MemberService memberService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		// 기타 유용한 정보를 request에 담는다.
				Map<String, Object> param = Util.getParamMap(request);
				String paramJson = Util.toJsonStr(param);

				String requestUrl = request.getRequestURI();
				String queryString = request.getQueryString();

				if (queryString != null && queryString.length() > 0) {
					requestUrl += "?" + queryString;
				}
				String encodedRequestUrl = Util.getUrlEncoded(requestUrl);
				
				request.setAttribute("param", param);
				request.setAttribute("paramJson", paramJson);
				request.setAttribute("requestUrl", requestUrl);
				request.setAttribute("encodedAfterLoginUrl", encodedRequestUrl);

				System.out.println("param : " + param);
				System.out.println("paramJson : " + paramJson);
				System.out.println("requestUrl : " + requestUrl);
				System.out.println("encodedRequestUrl : " + encodedRequestUrl);
		
				
				
				
				
		Member loginedMember = null;
		int loginedMemberId = 0;

		String authKey = request.getParameter("authKey");
		if (authKey != null && authKey.length() > 0) {
			loginedMember = memberService.getMemberByAuthKey(authKey);
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
