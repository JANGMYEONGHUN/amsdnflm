package com.spring.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.test.dao.MemberDAO;
import com.spring.test.vo.MemberVO;

@Service
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	public boolean join(String memberId, String memberPw) {
		MemberVO newMember = new MemberVO();
		newMember.setMemberId(memberId);
		newMember.setMemberPw(memberPw);
		
		int result = dao.join(newMember);
		
		if(result > 0) return true;
		else	return false;		
	}

	/*
	 * public boolean idCheck(String idCheck) { String result =
	 * dao.idCheck(idCheck); if(result == null) return true;// ID를 사용할 수 있는 경우 else
	 * return false; //ID를 사용할 수 없는 경우 }
	 */

	public String selectMember(String memberId, String memberPw) {
		MemberVO member = new MemberVO();
		member.setMemberId(memberId);
		member.setMemberPw(memberPw);
				
		return dao.selectMember(member);
	}
	
}
