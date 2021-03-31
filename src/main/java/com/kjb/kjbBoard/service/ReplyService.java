package com.kjb.kjbBoard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjb.kjbBoard.dao.ArticleDao;
import com.kjb.kjbBoard.dao.ReplyDao;
import com.kjb.kjbBoard.dto.Article;
import com.kjb.kjbBoard.dto.Reply;
import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.util.Util;

@Service
public class ReplyService {
	@Autowired
	private ReplyDao replyDao;
	@Autowired
	private ArticleDao articleDao;
	
	public ResultData getForPrintReplies(String relTypeCode, Integer relId) {
		if (relId == null) {
			return new ResultData("F-1", "게시글 번호를 확인해주세요.");
		}
		if(relTypeCode.equals("article")) {
			Article article = articleDao.getArticle(relId);
			if (article == null) {
				return new ResultData("F-2", "존재하지 않는 게시물 입니다.");
			}
		}
		// itemsInAPage : 몇 개씩 노출 시킬것인지
		int itemsInAPage = 10;
		List<Reply> replies = replyDao.getForPrintReplies(relTypeCode, relId);
		System.out.println(replies);
		return new ResultData("S-1", "댓글리스트" ,"replies" ,replies);
	}
	public ResultData addReply(Map<String, Object> param, HttpSession session) {
		int loginedMemberId = Util.getAsInt(session.getAttribute("loginedMemberId"), 0);
		param.put("memberId", loginedMemberId);
		if (param.get("articleId") == null) {
			return new ResultData("F-1", "articleId를 입력해주세요.");
		}
		if (param.get("body") == null) {
			return new ResultData("F-1", "body를 입력해주세요.");
		}
		replyDao.addReply(param);
		int id = Util.getAsInt(param.get("id"), 0);
		return new ResultData("S-1", "게시글이 작성되었습니다.", "id", id);
	}


}
