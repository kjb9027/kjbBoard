package com.kjb.kjbBoard.controller;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.service.ArticleService;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleServise;

	@RequestMapping("usr/article/detail")
	@ResponseBody
	public ResultData showDetail(Integer id) {
		return articleServise.getForPrintArticle(id);
	}

	@RequestMapping("usr/article/list")
	@ResponseBody
	public ResultData showList(String keywordType, String keyword, 
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int boardId) {
		int itemsInAPage = 20;
		return articleServise.getForPrintArticles(keywordType, keyword, page, itemsInAPage, boardId);
	}

	@RequestMapping("usr/article/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String, Object> param, HttpSession session) {
		return articleServise.add(param, session);
	}
	
	@RequestMapping("usr/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id, HttpSession session) {
		return articleServise.delete(id, session);
	}

	@RequestMapping("usr/article/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam Map<String, Object> param, HttpSession session) {
		return articleServise.modify(param, session);
	}
}
