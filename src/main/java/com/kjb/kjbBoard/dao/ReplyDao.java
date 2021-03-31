package com.kjb.kjbBoard.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kjb.kjbBoard.dto.Reply;

@Mapper
public interface ReplyDao {
	List<Reply> getForPrintReplies(@Param("relTypeCode") String relTypeCode, @Param("relId") Integer relId);

	void addReply(Map<String, Object> param);

}
