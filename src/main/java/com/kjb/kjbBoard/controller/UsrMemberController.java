package com.kjb.kjbBoard.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.service.MemberService;

@Controller
public class UsrMemberController {
	@Autowired
	private MemberService memberService;

	@RequestMapping("usr/member/doJoin")
	@ResponseBody
	public ResultData doJoin(@RequestParam Map<String, Object> param) {
		return memberService.joinMember(param);
	}

	@RequestMapping("usr/member/doLogin")
	@ResponseBody
	public ResultData doLogin(@RequestParam Map<String, Object> param, HttpSession session) {
		return memberService.loginMember(param, session);
	}

	@RequestMapping("usr/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {
		return memberService.logoutMember(session);
	}

	@RequestMapping("usr/member/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		return memberService.modifyMember(param, req);
	}
}
