package com.kjb.kjbBoard.controller;

import javax.servlet.http.HttpServletRequest;

public class BaseController {
	protected String msgAndBack (HttpServletRequest request, String msg) {
		request.setAttribute("historyBack", true);
		request.setAttribute("msg", msg);
		return "common/redirect";
	}
}
