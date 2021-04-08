package com.kjb.kjbBoard.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjb.kjbBoard.dao.MemberDao;
import com.kjb.kjbBoard.dto.Member;
import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.util.Util;

@Service
public class AdminService {
	@Autowired
	private MemberDao memberDao;

	public String loginMember(Map<String, Object> param, HttpSession session) {
		if (param.get("loginId") == null) {
			return Util.msgAndBack("id을 입력해주세요.");
		}
		if (param.get("loginPw") == null) {
			return Util.msgAndBack("pw를 입력해주세요.");
		}
		Member existMember = memberDao.getMemberByLoginId(param.get("loginId").toString());
		if (existMember == null) {
			return Util.msgAndBack("존재하지 않는 로그인아이디 입니다.");
		}
		if (existMember.getLoginPw().equals(param.get("loginPw")) == false) {
			return Util.msgAndBack("F-3");
		}
		if (isAdmin(existMember.getId()) == false) {
			return Util.msgAndBack("F-4");
		}
		session.setAttribute("loginedMemberId", existMember.getId());
		String msg = String.format("%s님 환영합니다", existMember.getNickName());
		return Util.msgAndReplace(msg, "../home/main");
	}

	public String modifyMember(Map<String, Object> param, HttpServletRequest req) {
		if (param.isEmpty()) {
			return Util.msgAndBack("수정 할 정보를 입력해주세요.");
		}
		int loginedMemberId = (int) req.getAttribute("loginedMemberId");
		param.put("id", loginedMemberId);
		memberDao.modifyMember(param);
		return Util.msgAndReplace("회원정보가 수정되었습니다.","../home/main");
	}

	public Member getMember(int loginedMemberId) {
		return memberDao.getMember(loginedMemberId);
	}

	public boolean isAdmin(int actorId) {
		return actorId == 1;
	}

}
