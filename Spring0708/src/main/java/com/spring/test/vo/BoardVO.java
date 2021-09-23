package com.spring.test.vo;

import lombok.Data;

@Data
public class BoardVO {

	private int boardNum; // 글 번호
	private String boardTitle; // 글 제목
	private String boardContent; //글 내용
	private String memberId; //작성자ID
	private String boardIndate; //작성일
	private int count; //조회수
}
