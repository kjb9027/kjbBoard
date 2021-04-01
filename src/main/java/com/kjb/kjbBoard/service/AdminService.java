package com.kjb.kjbBoard.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjb.kjbBoard.dao.MemberDao;
import com.kjb.kjbBoard.dto.Member;
import com.kjb.kjbBoard.dto.ResultData;

@Service
public class AdminService {
	@Autowired
	private MemberDao memberDao;

	public ResultData loginMember(Map<String, Object> param, HttpSession session) {
		if (param.get("loginId") == null) {
			return new ResultData("F-1", "id을 입력해주세요.");
		}
		if (param.get("loginPw") == null) {
			return new ResultData("F-1", "pw를 입력해주세요.");
		}
		Member existMember = memberDao.getMemberByLoginId(param.get("loginId").toString());
		if (existMember == null) {
			return new ResultData("F-2", "존재하지 않는 로그인아이디 입니다.", "loginId", param.get("loginId"));
		}
		if (existMember.getLoginPw().equals(param.get("loginPw")) == false) {
			return new ResultData("F-3", "비밀번호가 일치하지 않습니다.");
		}
		if (isAdmin(existMember.getId()) == false) {
			return new ResultData("F-4", "관리자 권한이 없습니다.");
		}
		session.setAttribute("loginedMemberId", existMember.getId());
		return new ResultData("S-1", String.format("%s님 환영합니다", existMember.getNickName()));
	}

	public ResultData modifyMember(Map<String, Object> param, HttpServletRequest req) {
		if (param.isEmpty()) {
			return new ResultData("F-2", "수정 할 정보를 입력해주세요.");
		}
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);
		memberDao.modifyMember(param);
		return new ResultData("S-1", "회원정보가 수정되었습니다.");
	}

	public Member getMember(int loginedMemberId) {
		return memberDao.getMember(loginedMemberId);
	}

	public boolean isAdmin(int actorId) {
		return actorId == 1;
	}

}
