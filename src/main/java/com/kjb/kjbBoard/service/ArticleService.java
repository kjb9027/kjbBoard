package com.kjb.kjbBoard.service;

import java.util.List;
import java.util.Map;

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
		return articleDao.getArticles(keyword, keywordType);
	}

	public ResultData add(Map<String,Object> param) {
		if(param.get("title")==null) {
			new ResultData("F-1", "title을 입력해주세요");
		}
		if(param.get("body")==null) {
			new ResultData("F-2", "body를 입력해주세요");
		}
		System.out.println(param);
		articleDao.addArticle(param);
		System.out.println(param);
		int id = Util.getAsInt(param.get("id"),0);
		return new ResultData("S-1", "게시글이 작성되었습니다 ", "id", id);
	}

	public ResultData delete(int id) {
		articleDao.deleteArticle(id);
		return new ResultData("S-1", "게시글이 삭제되었습니다 ", "id", id);
	}

	public ResultData modify(int id, String title, String body) {
		articleDao.modifyArticle(id, title, body);
		return new ResultData("S-1", "게시글이 수정되었습니다 ", "id", id);
	}
}
