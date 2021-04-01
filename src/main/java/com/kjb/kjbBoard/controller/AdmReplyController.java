package com.kjb.kjbBoard.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.service.ReplyService;

@Controller
public class AdmReplyController {
	@Autowired
	private ReplyService replyService;

	@RequestMapping("adm/reply/list")
	@ResponseBody
	public ResultData showList(String relTypeCode, Integer relId) {
		return replyService.getForPrintReplies(relTypeCode, relId);
	}

	@RequestMapping("adm/reply/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		return replyService.add(param, req);
	}

	@RequestMapping("adm/reply/doDelete")
	@ResponseBody
	public ResultData doDelete(int id, HttpServletRequest req) {
		return replyService.delete(id, req);
	}
	
	@RequestMapping("adm/reply/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		return replyService.modify(param, req);
	}

}
