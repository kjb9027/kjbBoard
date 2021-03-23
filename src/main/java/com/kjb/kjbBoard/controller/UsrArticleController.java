package com.kjb.kjbBoard.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjb.kjbBoard.dto.Article;
import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.util.Util;

@Controller
public class UsrArticleController {
	private int articlesLastId;
	private List<Article> articles;

	public UsrArticleController() {
		// 맴버 변수 초기화
		articlesLastId = 0;
		articles = new ArrayList<>();
		// 게시물 다섯개 생성
		articles.add(new Article(++articlesLastId, "2020-12-12 12:12:12", "2020-12-12 12:12:12", "제목1", "내용1"));
		articles.add(new Article(++articlesLastId, "2020-12-13 12:12:12", "2020-12-12 12:12:12", "제목2", "내용2"));
		articles.add(new Article(++articlesLastId, "2020-12-14 12:12:12", "2020-12-12 12:12:12", "제목3", "내용3"));
		articles.add(new Article(++articlesLastId, "2020-12-15 12:12:12", "2020-12-12 12:12:12", "제목4", "내용4"));
		articles.add(new Article(++articlesLastId, "2020-12-16 12:12:12", "2020-12-12 12:12:12", "제목5", "내용5"));
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
	public ResultData doAdd(String title, String body) {
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		articles.add(new Article(++articlesLastId, regDate, updateDate, title, body));
		return new ResultData("S-1", "성공하였습니다", "id", articlesLastId);
	}

	@RequestMapping("user/article/doDelete")
	@ResponseBody
	public ResultData doDelete(int id) {
		boolean deleteArticleRs = deleteArticle(id);
		if (deleteArticleRs) {
			return new ResultData( "S-1", "성공하였습니다");
		} else {
			return new ResultData( "F-1", "해당게시글은 존재하지않습니다");
		}
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
	public ResultData doModify(int id, String title, String body) {
		Article selArticle = null;
		for (Article article : articles) {
			if (article.getId() == id) {
				selArticle = article;
				selArticle.setUpdateDate(Util.getNowDateStr());
				selArticle.setTitle(title);
				selArticle.setBody(body);
				break;
			}
		}
		if (selArticle == null) {
			return new ResultData("F-1", String.format("%d번 게시물은 존재하지 않습니다.", id));
		} else {
			return new ResultData("S-1", String.format("%d번 게시글이 수정되었습니다.", id));
		}
	}
}
