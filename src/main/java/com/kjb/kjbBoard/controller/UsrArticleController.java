package com.kjb.kjbBoard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjb.kjbBoard.dto.Article;
import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.service.ArticleService;

@Controller
public class UsrArticleController {
	@Autowired
	private ArticleService articleServise;

	@RequestMapping("user/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		return articleServise.getArticle(id);
	}

	@RequestMapping("user/article/list")
	@ResponseBody
	public List<Article> showList(String keywordType, String keyword) {
		return articleServise.getArticles(keywordType, keyword);
	}

	@RequestMapping("user/article/doAdd")
	@ResponseBody
	public ResultData doAdd(@RequestParam Map<String,Object> param, HttpSession session) {
		return articleServise.add(param,session);
	}

	@RequestMapping("user/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id,HttpSession session) {
		return articleServise.delete(id,session);
	}

	@RequestMapping("user/article/doModify")
	@ResponseBody
	public ResultData doModify(@RequestParam Map<String,Object> param, HttpSession session) {
		return articleServise.modify(param,session);
	}
}
