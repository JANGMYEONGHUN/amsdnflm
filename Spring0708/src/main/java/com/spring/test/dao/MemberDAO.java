package com.spring.test.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.spring.test.vo.MemberVO;

@Repository
public class MemberDAO {
	@Autowired
	private SqlSession session;
	
	public int join(MemberVO newMember) {
		int result = 0;
		MemberMapper mapper = null;
		
		try {
			mapper = session.getMapper(MemberMapper.class);
			result = mapper.join(newMember);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return result;
	}
	public String idCheck(String idCheck) {
		String result = null;
		MemberMapper mapper = null;
		try {
			mapper = session.getMapper(MemberMapper.class);
			result = mapper.idCheck(idCheck);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	public String selectMember(MemberVO member) {
		String result = null;
		MemberMapper mapper = null;
		try {
			mapper = session.getMapper(MemberMapper.class);
			result = mapper.selectMember(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
