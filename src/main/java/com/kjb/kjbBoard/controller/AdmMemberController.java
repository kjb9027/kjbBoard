package com.kjb.kjbBoard.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjb.kjbBoard.service.AdminService;

@Controller
public class AdmMemberController {
	@Autowired
	private AdminService adminService;

	@RequestMapping("adm/member/doLogin")
	@ResponseBody
	public String doLogin(@RequestParam Map<String, Object> param, HttpSession session) {
		return adminService.loginMember(param, session);
	}
	@RequestMapping("adm/member/doLogout")
	@ResponseBody
	public String doLogout(HttpSession session) {
		return adminService.logoutMember(session);
	}
	@RequestMapping("adm/member/doModify")
	@ResponseBody
	public String doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		return adminService.modifyMember(param, req);
	}
}
