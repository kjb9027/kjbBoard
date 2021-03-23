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
		return articleDao.addArticle(title,body);
	}

	public ResultData delete(Integer id) {
		return articleDao.deleteArticle(id);
	}

	public ResultData modify(Integer id, String title, String body) {
		return articleDao.modifyArticle(id,title,body);
	}
}
