package com.kjb.kjbBoard.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.service.ArticleService;

@Controller
public class AdmArticleController {
	@Autowired
	private ArticleService articleServise;

	@RequestMapping("adm/article/detail")
	@ResponseBody
	public ResultData showDetail(Integer id) {
		return articleServise.getForPrintArticle(id);
	}

	@RequestMapping("adm/article/list")
	@ResponseBody
	public ResultData showList(String keywordType, String keyword, 
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int boardId) {
		int itemsInAPage = 20;
		return articleServise.getForPrintArticles(keywordType, keyword, page, itemsInAPage, boardId);
	}

	@RequestMapping("adm/article/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		return articleServise.add(param, req);
	}
	
	@RequestMapping("adm/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id, HttpServletRequest req) {
		return articleServise.delete(id, req);
	}

	@RequestMapping("adm/article/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam Map<String, Object> param, HttpServletRequest req) {
		return articleServise.modify(param, req);
	}
}
