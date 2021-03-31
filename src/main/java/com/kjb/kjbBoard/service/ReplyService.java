package com.kjb.kjbBoard.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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
	@Autowired
	private MemberService memberService;
	
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
	public ResultData addReply(Map<String, Object> param, HttpServletRequest req) {
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
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
	
	
	
	
	
	
	public ResultData deleteReply(int id, HttpServletRequest req) {
		if (id == 0) {
			return new ResultData("F-1", "id를 입력해주세요.");
		}
		Reply reply = replyDao.getReply(id);
		if(reply == null) {
			return new ResultData("F-3", "댓글이 존재하지 않습니다.");
		}
		Article article = articleDao.getArticle(reply.getRelId());
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		if (article == null) {
			return new ResultData("F-2", "게시글이 존재하지 않습니다.");
		}
		
		ResultData actorCanDeleteRd = actorCan(article,reply, loginedMemberId);
		if (actorCanDeleteRd.isFail()) {
			return actorCanDeleteRd;
		}
		replyDao.deleteReply(id);
		return new ResultData("S-1", "댓글이 삭제되었습니다.", "id", id);
	}
	private ResultData actorCan(Article article, Reply reply, int actorId) {
		if(article.getMemberId() == actorId){
			return new ResultData("S-1", "가능합니다.");
		}
		if (reply.getMemberId() == actorId) {
			return new ResultData("S-2", "가능합니다.");
		}
		if (memberService.isAdmin(actorId)) {
			return new ResultData("S-3", "가능합니다.");
		}
		return new ResultData("F-1", "권한이 없습니다.");
	}


}
