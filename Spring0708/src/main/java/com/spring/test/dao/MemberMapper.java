package com.spring.test.dao;

import com.spring.test.vo.MemberVO;

public interface MemberMapper {

	int join(MemberVO newMember);

	String idCheck(String idCheck);

	String selectMember(MemberVO member);	
	
}
