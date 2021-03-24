package com.kjb.kjbBoard.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kjb.kjbBoard.dto.Article;

@Mapper
public interface ArticleDao {
	public Article getArticle(
			@Param(value = "id") int id);

	public List<Article> getArticles(
			@Param(value = "keyword") String keyword,
			@Param(value = "keywordType") String keywordType);

	public void addArticle(
			@Param(value = "title") String title, 
			@Param(value = "body") String body);

	public void deleteArticle(
			@Param(value = "id") int id);

	public void modifyArticle(
			@Param(value = "id") int id, 
			@Param(value = "title") String title,
			@Param(value = "body") String body);

}
