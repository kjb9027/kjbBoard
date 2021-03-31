package com.kjb.kjbBoard.dto;

import lombok.Data;

@Data
public class Reply {
	private int id;
	private String regDate;
	private String updateDate;
	private int articleId;
	private int memberId;
	private String body;
}
