package com.kjb.kjbBoard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdmHomeController {
	@RequestMapping("adm/member/login")
	public String login() {
		return "adm/member/login";
	}
	@RequestMapping("adm/home/main")
	public String main() {
		return "adm/home/main";
	}
}
