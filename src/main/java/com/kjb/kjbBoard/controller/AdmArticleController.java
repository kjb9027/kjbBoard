package com.kjb.kjbBoard.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kjb.kjbBoard.dto.Article;
import com.kjb.kjbBoard.dto.Board;
import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.service.ArticleService;

@Controller
public class AdmArticleController extends BaseController {
	@Autowired
	private ArticleService articleService;

	@RequestMapping("adm/article/detail")
	public String showDetail(HttpServletRequest request, Integer id) {
		System.out.println(id);
		if (id == null) {
			return msgAndBack(request, "id를 입력해주세요.");
		}
		Article article = articleService.getForPrintArticle(id);
		if (article == null) {
			return msgAndBack(request, "게시글이 존재하지 않습니다.");
		} else {
			request.setAttribute("article", article);
		}
		return "adm/article/detail";
	}

	@RequestMapping("adm/article/list")
	public String showList(HttpServletRequest request, String keywordType, String keyword,
			@RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "1") int boardId, Model model) {
		Board board = articleService.getBoard(boardId);
		if (board == null) {
			return msgAndBack(request, "존재하지 않는 게시판입니다.");
		}
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
		// page : 몇 페이지를 노출 시킬것인지
		// itemsInAPage : 몇 개씩 노출 시킬것인지

		int itemsInAPage = 20;
		int limitStart = (page - 1) * itemsInAPage;
		int limitTake = itemsInAPage;
		List<Article> articles = articleService.getForPrintArticles(keywordType, keyword, limitStart, limitTake,
				boardId);
		request.setAttribute("articles", articles);
		return "adm/article/list";
	}

	@RequestMapping("adm/article/doAdd")
	@ResponseBody
	public ResultData doAdd(HttpServletRequest request, @RequestParam Map<String, Object> param) {
		int loginedMemberId = (int) request.getAttribute("loginedMemberId");
		if (param.get("title") == null) {
			return new ResultData("F-1", "title을 입력해주세요.");
		}
		if (param.get("body") == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}
		param.put("memberId", loginedMemberId);
		return articleService.add(param, request);
	}

	@RequestMapping("adm/article/doDelete")
	@ResponseBody
	public ResultData doDelete(HttpServletRequest request, int id, HttpServletRequest req) {
		return articleService.delete(id, req);
	}

	@RequestMapping("adm/article/doModify")
	@ResponseBody
	public ResultData doModify(HttpServletRequest request, @RequestParam Map<String, Object> param,
			HttpServletRequest req) {
		return articleService.modify(param, req);
	}
}
