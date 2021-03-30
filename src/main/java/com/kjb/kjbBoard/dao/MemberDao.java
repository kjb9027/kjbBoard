package com.kjb.kjbBoard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kjb.kjbBoard.dto.Member;


@Mapper
public interface MemberDao {
	void joinMember(Map<String, Object> param);

	Member getMemberByLoginId(@Param(value = "loginId") String loginId);

	Member getMember(@Param(value = "id") int id);

	void modifyMember(Map<String, Object> param);


}
