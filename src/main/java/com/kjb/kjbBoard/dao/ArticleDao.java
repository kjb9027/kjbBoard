package com.kjb.kjbBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kjb.kjbBoard.dto.Article;
import com.kjb.kjbBoard.dto.Board;

@Mapper
public interface ArticleDao {
	public Article getArticle(@Param("id") int id);
	
	public Article getForPrintArticle(@Param("id") int id);

	public List<Article> getForPrintArticles(
			@Param("keywordType") String keywordType, 
			@Param("keyword") String keyword,
			@Param("limitStart") int limitStart, 
			@Param("limitTake") int limitTake,
			@Param("boardId") int boardId);

	public void addArticle(Map<String,Object> param);

	public void deleteArticle(
			@Param("id") int id);

	public void modifyArticle(Map<String, Object> param);

	public Board getBoard(@Param("boardId") int boardId);

	public void addReply(Map<String, Object> param);




}
