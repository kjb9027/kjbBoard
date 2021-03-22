package com.kjb.kjbBoard.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjb.kjbBoard.dto.Article;

@Controller
public class UsrArticleController {
	private int articlesLastId;
	private List<Article> articles;

	public UsrArticleController() {
		// 맴버 변수 초기화
		articlesLastId = 0;
		articles = new ArrayList<>();
		// 게시물 다섯개 생성
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2020-12-13 12:12:12", "제목2", "내용2"));
		articles.add(new Article(++articlesLastId, "2020-12-14 12:12:12", "제목3", "내용3"));
		articles.add(new Article(++articlesLastId, "2020-12-15 12:12:12", "제목4", "내용4"));
		articles.add(new Article(++articlesLastId, "2020-12-16 12:12:12", "제목5", "내용5"));
	}

	@RequestMapping("user/article/detail")
	@ResponseBody
	public Article showDetail(int id) {
		return articles.get(id - 1);
	}

	@RequestMapping("user/article/list")
	@ResponseBody
	public List<Article> showList() {
		return articles;
	}

	@RequestMapping("user/article/doAdd")
	@ResponseBody
	public Map<String, Object> doAdd(String regDate, String title, String body) {
		articles.add(new Article(++articlesLastId, regDate, title, body));
		Map<String, Object> rs = new HashMap<>();
		rs.put("resultCode", "S-1");
		rs.put("msg", "성공하였습니다");
		rs.put("id", articlesLastId);
		return rs;
	}

	@RequestMapping("user/article/doDelete")
	@ResponseBody
	public Map<String, Object> doDelete(int id) {
		boolean deleteArticleRs = deleteArticle(id);
		Map<String, Object> rs = new HashMap<>();
		if (deleteArticleRs) {
			rs.put("resultCode", "S-1");
			rs.put("msg", "성공하였습니다");
		} else {
			rs.put("resultCode", "F-1");
			rs.put("msg", "해당게시글은 존재하지않습니다");
		}
		return rs;
	}

	private boolean deleteArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				articles.remove(article);
				return true;
			}
		}
		return false;
	}

	@RequestMapping("user/article/doModify")
	@ResponseBody
	public Map<String, Object> doModify(int id, String title, String body) {
		Article selArticle = null;
		for (Article article : articles) {
			if(article.getId()==id) {
				selArticle=article;
				selArticle.setTitle(title);
				selArticle.setBody(body);
				break;
			}
		}
		Map<String, Object> rs = new HashMap<>();
		if (selArticle==null) {
			rs.put("resultCode", "F-1");
			rs.put("msg", String.format("%d번 게시물은 존재하지 않습니다.", id));
		} else {
			rs.put("resultCode", "S-1");
			rs.put("msg", String.format("%d번 게시글이 수정되었습니다.", id));
		}
		return rs;
	}
}
