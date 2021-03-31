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
import com.kjb.kjbBoard.service.ReplyService;

@Controller
public class UsrReplyController {
	@Autowired
	private ReplyService replyService;
	
	@RequestMapping("usr/reply/list")
	@ResponseBody
	public ResultData showList(String relTypeCode, Integer relId) {
		return replyService.getForPrintReplies(relTypeCode, relId);
	}
	
	@RequestMapping("usr/reply/doAdd")
	@ResponseBody
	public ResultData doAddReply(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		return replyService.addReply(param, req);
	}
	@RequestMapping("usr/reply/doDelete")
	@ResponseBody
	public ResultData doDeleteReply(int id, HttpServletRequest req) {
		System.out.println(id);
		return replyService.deleteReply(id, req);
	}
	
}
