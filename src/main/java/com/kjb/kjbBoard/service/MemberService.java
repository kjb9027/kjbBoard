package com.kjb.kjbBoard.service;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjb.kjbBoard.dao.MemberDao;
import com.kjb.kjbBoard.dto.Member;
import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.util.Util;

@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	public ResultData joinMember(Map<String, Object> param) {
		if (param.get("loginId") == null) {
			return new ResultData("F-1", "id을 입력해주세요.");
		}
		Member existMember = memberDao.getMember(param.get("loginId").toString());
		if (existMember != null) {
			return new ResultData("F-2", String.format("%s (은)는 이미 사용중인 아이디 입니다.", existMember.getId()));
		}
		if (param.get("loginPw") == null) {
			return new ResultData("F-1", "pw를 입력해주세요.");
		}
		if (param.get("name") == null) {
			return new ResultData("F-1", "name을 입력해주세요.");
		}
		if (param.get("nickName") == null) {
			return new ResultData("F-1", "nickName를 입력해주세요.");
		}
		if (param.get("phoneNo") == null) {
			return new ResultData("F-1", "phoneNo을 입력해주세요.");
		}
		if (param.get("email") == null) {
			return new ResultData("F-1", "email를 입력해주세요.");
		}
		memberDao.joinMember(param);
		int id = Util.getAsInt(param.get("id"), 0);
		System.out.println(param.get("nickName"));
		return new ResultData("S-1", String.format("%s님 환영합니다", param.get("nickName")), "id", id);
	}

	public ResultData loginMember(Map<String, Object> param, HttpSession session) {
		if (session.getAttribute("loginedMemberId") != null) {
			return new ResultData("F-4", "이미 로그인되어있습니다.");
		}
		if (param.get("loginId") == null) {
			return new ResultData("F-1", "id을 입력해주세요.");
		}
		if (param.get("loginPw") == null) {
			return new ResultData("F-1", "pw를 입력해주세요.");
		}
		Member existMember = memberDao.getMember(param.get("loginId").toString());
		if (existMember == null) {
			return new ResultData("F-2", "존재하지 않는 로그인아이디 입니다.", "loginId", param.get("loginId"));
		}
		if (existMember.getLoginPw().equals(param.get("loginPw")) == false) {
			return new ResultData("F-3", "비밀번호가 일치하지 않습니다.");
		}
		session.setAttribute("loginedMemberId", existMember.getId());
		return new ResultData("S-1", String.format("%s님 환영합니다", existMember.getNickName()));
	}

}
