package com.kjb.kjbBoard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import com.kjb.kjbBoard.dao.ArticleDao;
import com.kjb.kjbBoard.dto.Article;
import com.kjb.kjbBoard.dto.Board;
import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.util.Util;

@Service
public class ArticleService {
	@Autowired
	private ArticleDao articleDao;
	@Autowired
	private MemberService memberService;

	private Article getArticle(int id) {
		return articleDao.getArticle(id);
	}

	public Article getForPrintArticle(Integer id) {
		return articleDao.getForPrintArticle(id);
	}

	public List<Article> getForPrintArticles(String keywordType, String keyword, int limitStart, int limitTake,
			int boardId) {
		return articleDao.getForPrintArticles(keywordType, keyword, limitStart, limitTake, boardId);
	}

	public ResultData add(Map<String, Object> param, HttpServletRequest req) {
		articleDao.addArticle(param);
		int id = Util.getAsInt(param.get("id"), 0);
		return new ResultData("S-1", "게시글이 작성되었습니다.", "id", id);
	}

	public ResultData delete(int id, HttpServletRequest req) {
		Article article = getArticle(id);
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		if (article == null) {
			return new ResultData("F-4", "게시글이 존재하지 않습니다.");
		}
		if (id == 0) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		ResultData actorCanDeleteRd = actorCan(article, loginedMemberId);
		if (actorCanDeleteRd.isFail()) {
			return actorCanDeleteRd;
		}
		articleDao.deleteArticle(id);
		return new ResultData("S-1", "게시글이 삭제되었습니다.", "id", id);
	}

	public ResultData modify(Map<String, Object> param, HttpServletRequest req) {
		int id = Integer.parseInt(param.get("id").toString());
		Article article = getArticle(id);
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		if (article == null) {
			return new ResultData("F-4", "게시글이 존재하지 않습니다.");
		}
		if (id == 0) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		if (loginedMemberId == 0) {
			return new ResultData("F-2", "로그인 후 이용해주세요.");
		}
		if (param.get("title") == null && param.get("body") == null) {
			return new ResultData("F-3", "수정 할 내용이 없습니다");
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
		if (memberService.isAdmin(actorId)) {
			return new ResultData("S-2", "가능합니다.");
		}
		return new ResultData("F-1", "권한이 없습니다.");
	}

	public Board getBoard(int boardId) {
		return articleDao.getBoard(boardId);
	}
}
