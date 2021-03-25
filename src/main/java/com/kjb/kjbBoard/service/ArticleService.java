package com.kjb.kjbBoard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjb.kjbBoard.dao.ArticleDao;
import com.kjb.kjbBoard.dto.Article;
import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.util.Util;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;

	public Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public List<Article> getArticles(String keywordType, String keyword) {
		if(keywordType != null) {
			keywordType = keywordType.trim();
		}
		if(keywordType == null || keywordType.length() == 0) {
			keywordType = "titleAndBody";
		}
		if(keyword != null && keyword.length() == 0) {
			keywordType = null;
		}
		if(keyword != null) {
			keyword = keyword.trim();
		}
		if(keyword == null) {
			keywordType = null;
		}
		
		return articleDao.getArticles(keyword, keywordType);
	}

	public ResultData add(Map<String,Object> param, HttpSession session) {
		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);
		if(loginedMemberId == 0) {
			return new ResultData("F-2", "로그인 후 이용해주세요.");
		}
		if(param.get("title")==null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}
		if(param.get("body")==null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}
		param.put("memberId", loginedMemberId);
		articleDao.addArticle(param);
		int id = Util.getAsInt(param.get("id"),0);
		return new ResultData("S-1", "게시글이 작성되었습니다.", "id", id);
	}

	public ResultData delete(int id) {
		articleDao.deleteArticle(id);
		return new ResultData("S-1", "게시글이 삭제되었습니다.", "id", id);
	}

	public ResultData modify(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
		return new ResultData("S-1", "게시글이 수정되었습니다.", "id", id);
	}
}
