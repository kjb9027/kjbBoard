package com.kjb.kjbBoard.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
		return articleServise.getAticle(id);
	}

	@RequestMapping("user/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articleServise.getArticles();
	}

	@RequestMapping("user/article/doAdd")
	@ResponseBody
	public ResultData doAdd(String title, String body) {
		return articleServise.add(title, body);
	}

	@RequestMapping("user/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		return articleServise.delete(id);
	}

	@RequestMapping("user/article/doModify")
	@ResponseBody
	public ResultData doModify(int id, String title, String body) {
		return articleServise.modify(id, title, body);
	}
}
