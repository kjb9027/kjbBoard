package com.kjb.kjbBoard.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kjb.kjbBoard.dao.MemberDao;
import com.kjb.kjbBoard.dto.ResultData;
import com.kjb.kjbBoard.util.Util;
@Service
public class MemberService {
	@Autowired
	private MemberDao memberDao;

	public ResultData joinMember(Map<String, Object> param) {
		if(param.get("loginId")==null) {
			return new ResultData("F-1", "id을 입력해주세요.");
		}
		if(param.get("loginPw")==null) {
			return new ResultData("F-1", "pw를 입력해주세요.");
		}
		if(param.get("name")==null) {
			return new ResultData("F-1", "name을 입력해주세요.");
		}
		if(param.get("nickName")==null) {
			return new ResultData("F-1", "nickName를 입력해주세요.");
		}
		if(param.get("phoneNo")==null) {
			return new ResultData("F-1", "phoneNo을 입력해주세요.");
		}
		if(param.get("email")==null) {
			return new ResultData("F-1", "email를 입력해주세요.");
		}
		memberDao.joinMember(param);
		int id = Util.getAsInt(param.get("id"),0);
		System.out.println(param.get("nickName"));
		return new ResultData("S-1", String.format("%s님 환영합니다", param.get("nickName")), "id", id);
	}

}
