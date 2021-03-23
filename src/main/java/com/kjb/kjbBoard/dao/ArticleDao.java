package com.kjb.kjbBoard.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.kjb.kjbBoard.dto.Article;
import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.util.Util;

@Component
public class ArticleDao {
	private int articlesLastId;
	private List<Article> articles;

	public ArticleDao() {
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

	public Article getArticle(int id) {
		for (Article article : articles) {
			if (article.getId() == id) {
				return article;
			}
		}
		return null;
	}

	public List<Article> getArticles(String keyword, String keywordType) {
		if (keywordType != null) {
			keywordType = keywordType.trim();
		}
		if (keywordType == null || keywordType.length() == 0) {
			keywordType = "titleAndBody";
		}
		if (keyword != null && keyword.length() == 0) {
			keyword = null;
		}
		if (keyword != null) {
			keyword = keyword.trim();
		}
		if (keyword == null) {
			return articles;
		}
		List<Article> filterList = new ArrayList<>();
		for (Article article : articles) {
			boolean contains = false;
			if (keywordType.equals("title")) {
				contains = article.getTitle().contains(keyword);
			} else if (keywordType.equals("body")) {
				contains = article.getBody().contains(keyword);
			} else {
				contains = article.getTitle().contains(keyword) || article.getBody().contains(keyword);
			}
			if (contains) {
				filterList.add(article);
			}
		}
		return filterList;
	}

	public ResultData addArticle(String title, String body) {
		if (title == null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}
		if (body == null) {
			return new ResultData("F-2", "body을 입력해주세요.");
		}
		String regDate = Util.getNowDateStr();
		String updateDate = regDate;
		int id = ++articlesLastId;
		articles.add(new Article(id, regDate, updateDate, title, body));
		return new ResultData("S-1", "성공하였습니다", "id", id);
	}

	public ResultData deleteArticle(Integer id) {
		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		if (getArticle(id) != null) {
			for (Article article : articles) {
				if (article.getId() == id) {
					articles.remove(article);
					break;
				}
			}
			return new ResultData("S-1", "성공하였습니다");
		} else {
			return new ResultData("F-1", "해당게시글은 존재하지않습니다");
		}
	}

	public ResultData modifyArticle(Integer id, String title, String body) {
		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		if (title == null) {
			return new ResultData("F-2", "title을 입력해주세요.");
		}
		if (body == null) {
			return new ResultData("F-3", "body을 입력해주세요.");
		}
		if (getArticle(id) != null) {
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
			return new ResultData("S-1", String.format("%d번 게시글이 수정되었습니다.", id));
		} else {
			return new ResultData("F-1", String.format("%d번 게시물은 존재하지 않습니다.", id));
		}
	}

}
