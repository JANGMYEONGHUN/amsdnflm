package com.spring.test.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor //기본생성자
@AllArgsConstructor //모든필드(인자)를 활(사)용한 생성자를 만듦
public class ReplyVO {
	
	
	@NonNull private int boardNum; //RequiredArgsConstructor 생성자를 만들어주는데 요구되는 인자들(NonNull이 붙은 필드)로 만듦
	@NonNull private String memberId;
	@NonNull private String replyContent;
	private String replyIndate;
	private int replyNum;
}
