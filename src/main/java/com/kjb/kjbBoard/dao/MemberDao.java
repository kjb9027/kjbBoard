package com.kjb.kjbBoard.dao;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
	void joinMember(Map<String, Object> param);
}
