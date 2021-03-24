package com.kjb.kjbBoard.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjb.kjbBoard.dao.ArticleDao;
import com.kjb.kjbBoard.dto.Article;
import com.kjb.kjbBoard.dto.ResultData;

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

	public ResultData add(String title, String body) {
		int id = 1; // 임시
		articleDao.addArticle(title, body);
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
