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
	@Autowired
	private MemberService memberService;

	public ResultData getForPrintArticle(Integer id) {
		if (id == null) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		Article article = articleDao.getForPrintArticle(id);
		if (article == null) {
			return new ResultData("F-2", "게시글이 존재하지 않습니다.");

		}
		return new ResultData("S-1", "성공.", "article", article);
	}

	private Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public ResultData getForPrintArticles(String keywordType, String keyword) {
		if (keywordType != null) {
			keywordType = keywordType.trim();
		}
		if (keywordType == null || keywordType.length() == 0) {
			keywordType = "titleAndBody";
		}
		if (keyword != null && keyword.length() == 0) {
			keywordType = null;
		}
		if (keyword != null) {
			keyword = keyword.trim();
		}
		if (keyword == null) {
			keywordType = null;
		}

		List<Article> articles= articleDao.getForPrintArticles(keyword, keywordType);
		return new ResultData("S-1", "리스트 출력 성공", "articles", articles);
	}


	public ResultData add(Map<String, Object> param, HttpSession session) {
		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);
		if (loginedMemberId == 0) {
			return new ResultData("F-2", "로그인 후 이용해주세요.");
		}
		if (param.get("title") == null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}
		if (param.get("body") == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}
		param.put("memberId", loginedMemberId);
		articleDao.addArticle(param);
		int id = Util.getAsInt(param.get("id"), 0);
		return new ResultData("S-1", "게시글이 작성되었습니다.", "id", id);
	}

	public ResultData delete(int id, HttpSession session) {
		Article article = getArticle(id);
		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);
		if (article == null) {
			return new ResultData("F-4", "게시글이 존재하지 않습니다.");
		}
		if (id == 0) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		if (loginedMemberId == 0) {
			return new ResultData("F-2", "로그인 후 이용해주세요.");
		}
		ResultData actorCanDeleteRd = actorCan(article, loginedMemberId);
		if (actorCanDeleteRd.isFail()) {
			return actorCanDeleteRd;
		}
		articleDao.deleteArticle(id);
		return new ResultData("S-1", "게시글이 삭제되었습니다.", "id", id);
	}

	public ResultData modify(Map<String, Object> param, HttpSession session) {
		int id = Integer.parseInt(param.get("id").toString());
		System.out.println(id);
		Article article = getArticle(id);
		System.out.println(article);
		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);
		if (article == null) {
			return new ResultData("F-4", "게시글이 존재하지 않습니다.");
		}
		if (id == 0) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		if (loginedMemberId == 0) {
			return new ResultData("F-2", "로그인 후 이용해주세요.");
		}
		ResultData actorCanModifyRd = actorCan(article, loginedMemberId);
		if (actorCanModifyRd.isFail()) {
			return actorCanModifyRd;
		}

		articleDao.modifyArticle(param);
		return new ResultData("S-1", "게시글이 수정되었습니다.", "id", param.get("id"));
	}

	private ResultData actorCan(Article article, int actorId) {
		if (article.getMemberId() == actorId) {
			return new ResultData("S-1", "가능합니다.");
		}
		if (memberService.idAdmin(actorId)) {
			return new ResultData("S-2", "가능합니다.");
		}
		return new ResultData("F-1", "권한이 없습니다.");
	}

}
