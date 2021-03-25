package com.kjb.kjbBoard.controller;

import java.util.Map;

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

	@RequestMapping("user/member/doJoin")
	@ResponseBody
	public ResultData doJoin(@RequestParam Map<String,Object> param) {
		return memberService.joinMember(param);
	}
	@RequestMapping("user/member/doLogin")
	@ResponseBody
	public ResultData doLogin(@RequestParam Map<String,Object> param, HttpSession session) {
		return memberService.loginMember(param,session);
	}
	@RequestMapping("user/member/doLogout")
	@ResponseBody
	public ResultData doLogout(HttpSession session) {
		return memberService.logoutMember(session);
	}
	@RequestMapping("user/member/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam Map<String,Object> param,HttpSession session) {
		return memberService.modifyMember(param,session);
	}
}
